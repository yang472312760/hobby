/**
 * AjaxAuthenticationFailureHandler.java
 * Created at 2019-7-30
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.security;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: AjaxAuthenticationFailureHandler
 * </p>
 * <p>
 * Description: 自定义登录失败处理器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            responseBody.setMessage("账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            responseBody.setMessage("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            responseBody.setMessage("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            responseBody.setMessage("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            responseBody.setMessage("账户被禁用，请联系管理员!");
        } else {
            responseBody.setMessage("登录失败!");
        }
        responseBody.setStatus(400);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
