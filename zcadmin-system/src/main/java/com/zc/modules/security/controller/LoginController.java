package com.zc.modules.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.zc.annoation.Anonymous;
import com.zc.annotation.Log;
import com.zc.constant.ResultCode;
import com.zc.entity.JwtAuthentication;
import com.zc.entity.ResultResponse;
import com.zc.exception.BadRequestException;
import com.zc.jwt.JwtUtil;
import com.zc.modules.security.entity.OnlineUserDto;
import com.zc.modules.security.entity.User;
import com.zc.modules.security.service.OnlineUserService;
import com.zc.modules.system.mapper.UserMapper;
import com.zc.utils.RedisUtil;
import com.zc.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangC
 * @create 2021-08-02-17:23
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OnlineUserService onlineUserService;

    @Log("??????")
    @PostMapping("/system/login")
    public ResultResponse login(@RequestBody User user, HttpServletRequest request) {
        //????????? ?????? ?????????????????????
        if (!"phoneVerify".equals(user.getVerifyType()) && !"thirdVerify".equals(user.getVerifyType())) {
//            request.setAttribute("verifyType","password");
            String uuid = user.getUuid();
            String code = null;
            if (uuid != null) {
                code = RedisUtil.StringOps.get(uuid);
                RedisUtil.KeyOps.delete(uuid);
            }
            if (uuid == null || user.getVerifyCode() == null || code == null) {
                throw new BadRequestException("?????????????????????,???????????????");
            }
            MathGenerator mathGenerator = new MathGenerator();
            boolean verify = mathGenerator.verify(code, user.getVerifyCode());
            if (!verify) {
                // ?????????????????????
                throw new BadRequestException(ResultCode.VERIFICATION_CODE_ERROR);
            }

        }

        log.info("user??????:" + user.toString());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        com.zc.modules.system.entity.User userInfo = userMapper.selectSysUserByUserName(user.getUsername());
        if (userInfo==null){
            return ResultResponse.error("???????????????");
        }
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //?????????:??????jwt?????????,?????????
        JwtAuthentication jwtAuthentication=new JwtAuthentication(
             authenticationToken
        );
        jwtAuthentication.setNickName(userInfo.getNickName());
        String token = jwtUtil.JWTCreator(jwtAuthentication);
        OnlineUserDto build = OnlineUserDto.builder()
                .username(userInfo.getUsername())
                .nickName(userInfo.getNickName())
                .address(com.zc.utils.StringUtils.getAddress(request))
                .ip(com.zc.utils.StringUtils.getIp(request))
                .browser(com.zc.utils.StringUtils.getBrowser(request))
                .token(token)
                .loginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
        RedisUtil.StringOps.setEx(
                "online:user:"+token, JSONObject.toJSONString(build),3, TimeUnit.MINUTES
        );

        Map<String, Object> map = new HashMap();
        map.put("token", token);
        return ResultResponse.success(map);
    }

    @ApiOperation("??????????????????")
    @GetMapping("online/user")
    public ResultResponse query(Pageable pageable){
        return ResultResponse.success(onlineUserService.getAll(pageable));
    }



    @Log("????????????")
    @GetMapping("/system/userinfo")
    public ResultResponse getInfo() {
        String username = SecurityUtils.getCurrentUsername();
        Set<String> rolesSet = userMapper.selectRolesByUsername(username);


        Map<String, Object> map = new HashMap();
        map.put("roles", rolesSet);
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");
        return ResultResponse.success(map);
    }


    @Log("??????")
    @PostMapping("/user/logout")
    public ResultResponse logout(HttpServletRequest request) {
        /**
         * ????????????????????????
         */
        String token = request.getHeader("X-Token");
        if (StringUtils.isNotEmpty(token)) {
            RedisUtil.StringOps.setEx("jwt-black:list:" + token, "????????????", 8, TimeUnit.DAYS);
        }
        return ResultResponse.success("success");
    }

    @Log("??????")
    @PostMapping("/user/kickOut")
    public ResultResponse kickOut(@RequestBody OnlineUserDto onlineUserDto) {
        if (onlineUserDto.getToken()==null){
            return ResultResponse.error("??????:?????????????????????");
        }
        onlineUserService.kickOut(onlineUserDto.getToken());
        return ResultResponse.success("success");
    }



    @GetMapping("auth/code")
    @Anonymous
    public ResultResponse getCode() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // ?????????????????????????????????????????????
        captcha.setGenerator(new MathGenerator(1));
        // ????????????code
        captcha.createCode();

        String uuid = "code:key:" + IdUtil.simpleUUID();


        // ??????
        RedisUtil.StringOps.setEx(uuid, captcha.getCode(), 1, TimeUnit.MINUTES);
        // ???????????????
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.getImageBase64Data());
            put("uuid", uuid);
        }};
        return ResultResponse.success(imgResult);
    }
}
