package com.zc.entity;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author ZhangC
 * @create 2021-08-02-17:05
 */
@Data
public class UserDto implements UserDetails {


    /**
     * userDetails 有几个方法分别对应着:
     * 用户的权限集， 默认需要添加ROLE_ 前缀
     * 用户的加密后的密码， 不加密会使用{noop}前缀
     * 应用内唯一的用户名
     * 账户是否过期
     * 账户是否锁定
     * 凭证是否过期
     * 用户是否可用
     */

    String username;
    String password;
    Set<String> permission;
    List<GrantedAuthority> authorities;
    Long userId;

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        for (String s : this.permission) {
            if (StringUtils.isNotBlank(s)) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(s);
                list.add(grantedAuthority);
            }
        }

        return list;
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
