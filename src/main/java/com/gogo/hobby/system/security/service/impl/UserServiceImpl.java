/**
 * UserServiceImpl.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gogo.hobby.system.security.entity.RoleToken;
import com.gogo.hobby.system.security.entity.User;
import com.gogo.hobby.system.security.entity.UserToken;
import com.gogo.hobby.system.security.mapper.IUserMapper;
import com.gogo.hobby.system.security.service.IRoleService;
import com.gogo.hobby.system.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * 
 * <p>
 * ClassName: UserServiceImpl
 * </p>
 * <p>
 * Description: 用户服务实现类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
    
    /**
     * 用户接口
     */
    @Autowired
    private IUserMapper userMapper;
    
    /**
     * 角色服务
     */
    @Autowired
    private IRoleService roleService;

    @Override
    public User getUserByLoginName(String loginName) {
        User user = new User();
        user.setUserName(loginName);
        return this.userMapper.getUserByLoginName(user);
    }

    @Override
    public UserToken getUserByUserName(String userName) {

        UserToken userToken = new UserToken();
        userToken.setUsername(userName);
        UserToken user = this.userMapper.getUserByUserName(userToken);
        if (user != null) {
            user.setAuthorities(new HashSet<>(this.roleService.getListByUserId(user.getId())));
        }
        return user;

    }

    @Override
    public User getUserById(Integer id) {
        return this.userMapper.selectById(id);
    }
}
