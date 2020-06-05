/**
 * BaseAuthenticationProvider.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import com.gogo.hobby.system.security.entity.UserToken;
import com.gogo.hobby.system.security.service.BaseUserDetailsService;
import com.gogo.hobby.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: BaseAuthenticationProvider
 * </p>
 * <p>
 * Description: 自定义身份验证
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class BaseAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户明细服务
     */
    @Autowired
    BaseUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserToken user = (UserToken) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserToken userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        if (userDetails != null) {
            if (!userDetails.getPassword().equals(Md5Util.encode(password))) {
                throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
            }
        } else {
            throw new BadCredentialsException("无此用户，请确认！");
        }
        userDetails.setIsRememberMe(user.getIsRememberMe());
        userDetails.setPassword(password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
