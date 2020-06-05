/**
 * JsonWebTokenAuthorizationFilter.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.filter;

import com.gogo.hobby.system.security.entity.UserToken;
import com.gogo.hobby.util.JsonWebTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * 
 * <p>
 * ClassName: JsonWebTokenAuthorizationFilter
 * </p>
 * <p>
 * Description: 用户授权拦截器
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
public class JsonWebTokenAuthorizationFilter extends BasicAuthenticationFilter {
    
    /**
     * 
     * <p>Description: 构造器</p>
     * @param authenticationManager 验证管理
     */
    public JsonWebTokenAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            String tokenHeader = request.getHeader(JsonWebTokenUtil.TOKEN_HEADER);
            if (tokenHeader == null || !tokenHeader.startsWith(JsonWebTokenUtil.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * <p>
     * Description: 校验token
     * </p>
     * 
     * @param tokenHeader token
     * @return UsernamePasswordAuthenticationToken 用户名密码验证token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JsonWebTokenUtil.TOKEN_PREFIX, "");
        UserToken user = JsonWebTokenUtil.getUser(token);
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    user.getAuthorities());
        }
        return null;
    }

}
