package com.zc.modules.security.config;

/**
 * @author ZhangC
 * @create 2021-08-02-17:14
 */

import com.zc.annoation.Anonymous;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.filter.TokenFilter;
import com.zc.utils.RequestMethodUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final WebApplicationContext applicationContext;

    /**
     * ???swagger????????????
     * https://stackoverflow.com/questions/37671125/how-to-configure-spring-security-to-allow-swagger-url-to-be-accessed-without-aut
     */
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/doc.html",
            "/tool/gen/**",
            "**"
//            "/project/bookInfo/**"
            // other public endpoints of your API may be appended to this array
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        Map<String, Set<String>> anonymousUrls = getAnonymousUrls();
        http
                .csrf().disable()      //???????????????,???????????????post??????????????????
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, anonymousUrls.get("GET").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.POST, anonymousUrls.get("POST").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.PATCH, anonymousUrls.get("PATCH").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.DELETE, anonymousUrls.get("DELETE").toArray(new String[0])).permitAll()
                .antMatchers(HttpMethod.PUT, anonymousUrls.get("PUT").toArray(new String[0])).permitAll()
                .antMatchers(anonymousUrls.get("ALL").toArray(new String[0])).permitAll()

//                .antMatchers("/**").permitAll()
                //???????????????????????????????????????
                .anyRequest().authenticated()
        ;
        http.addFilterBefore(new TokenFilter(authenticationManagerBuilder, jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));

        //'Access-Control-Allow-Origin
        //setAllowedOriginPatterns ??? setAllowedOrigins ????????????
        //setAllowCredentials(true) ??? setAllowedOriginPatterns ????????????
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Content-Length", "Accept-Encoding", "X-CSRF-Token", "Authorization", "X-Max", "x-token"));
        configuration.addExposedHeader("Content-Length, Access-Control-Allow-Origin, Access-Control-Allow-Headers");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ??????????????????
        return new BCryptPasswordEncoder();
    }

    public Map<String, Set<String>> getAnonymousUrls() {
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        anonymousUrls.put("GET", new HashSet<>());
        anonymousUrls.put("PUT", new HashSet<>());
        anonymousUrls.put("POST", new HashSet<>());
        anonymousUrls.put("PATCH", new HashSet<>());
        anonymousUrls.put("DELETE", new HashSet<>());
        anonymousUrls.put("ALL", new HashSet<>());
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // ??????url??????????????????????????????
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.forEach((k, v) -> {
            if (v.hasMethodAnnotation(Anonymous.class)) {

                ArrayList<RequestMethod> requestMethods = new ArrayList<>(k.getMethodsCondition().getMethods());
                Set<String> strings = anonymousUrls.get(RequestMethodUtil.find(requestMethods.size() == 0 ? "All" : requestMethods.get(0).name()));
                strings.addAll(k.getPatternsCondition().getPatterns());
            }
        });

        return anonymousUrls;
    }

    //???????????????????????????bean???????????????,????????????????????????
    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myAuthenticationProvider.setUserDetailsService(userDetailsService);
        return myAuthenticationProvider;
    }

}
