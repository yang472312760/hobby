/**
 * UserService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gogo.hobby.system.security.entity.User;
import com.gogo.hobby.system.security.entity.UserToken;

/**
 * 
 * <p>
 * ClassName: IUserService
 * </p>
 * <p>
 * Description: 角色服务接口
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
public interface IUserService extends IService<User> {

    /**
     * 
     * <p>
     * Description: 根据用户名获取User
     * </p>
     * 
     * @param loginName 用户名
     * @return User 用户
     */
    public User getUserByLoginName(String loginName);

    /**
     * 
     * <p>
     * Description: 根据用户名获取User
     * </p>
     * 
     * @param userName 用户名
     * @return UserToken 用户
     */
    public UserToken getUserByUserName(String userName);
    
    /**
     * 
     * <p>Description: 根据ID获取用户</p>
     * @param id 用户ID
     * @return User 用户
     */
    public User getUserById(Integer id);

}
