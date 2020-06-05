/**
 * JsonWebTokenAuthenticationFilter.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.filter;

import com.gogo.hobby.config.security.AjaxAuthenticationSuccessHandler;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gogo.hobby.system.security.entity.UserToken;
import com.gogo.hobby.util.JsonWebTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * <p>
 * ClassName: JsonWebTokenAuthenticationFilter
 * </p>
 * <p>
 * Description: 用户登录认证拦截器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
public class JsonWebTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    /**
     * 验证管理器
     */
    private AuthenticationManager authenticationManager;
    
    /**
     * 验证成功处理器
     */
    private AuthenticationSuccessHandler authenticationSuccessHandler = new AjaxAuthenticationSuccessHandler();
    
    /**
     * 
     * <p>Description: 构造器</p>
     * @param authenticationManager 验证管理
     */
    public JsonWebTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserToken user = new UserToken(request.getParameter("username"), request.getParameter("password"));
            String isRememberMeTemp = request.getParameter("isRememberMe");
            Boolean isRememberMe = false;
            String a = "1";
            if (isRememberMeTemp != null && !"".equals(isRememberMeTemp) && isRememberMeTemp.equals(a)) {
                isRememberMe = true;
            }
            user.setIsRememberMe(isRememberMe);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user,
                    user.getPassword(), null);
            return this.authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserToken user = (UserToken) authResult.getPrincipal();
        String token = JsonWebTokenUtil.createToken(user, user.getIsRememberMe());
        response.setHeader("token", JsonWebTokenUtil.TOKEN_PREFIX + token);
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
