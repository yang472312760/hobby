/**
 * BaseUserDetailsService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service;

import com.gogo.hobby.system.security.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * ClassName: BaseUserDetailsService
 * </p>
 * <p>
 * Description: 用户服务
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Component
public class BaseUserDetailsService implements UserDetailsService {

    /**
     * 用户服务
     */
    @Autowired
    private IUserService userService;

    @Override
    public UserToken loadUserByUsername(String username) throws UsernameNotFoundException {
        UserToken user = this.userService.getUserByUserName(username);
        return user;
    }
}
