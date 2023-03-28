package com.kang.security;

import cn.hutool.core.lang.Assert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/23 22:23
 */
public class AccountUser implements UserDetails {

    private Long userId;

    private String password;

    private final String username;

    private final List<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    /**
     * 凭证是否过期
      */
    private final boolean credentialsNonExpired;

    /**
     * 用户是否可用
     */
    private final boolean enabled;


    public AccountUser(Long userId, String password, String username, List<GrantedAuthority> authorities) {
        this(userId,password,username,authorities,true,true,true,true);
    }

    public AccountUser(Long userId, String password, String username, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        Assert.isTrue(username!=null && !"".equals(username) && password !=null,"Cannot pass null or empty values to constructor");
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
