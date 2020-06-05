/**
 * UserMapper.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gogo.hobby.system.security.entity.User;
import com.gogo.hobby.system.security.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 
 * <p>ClassName: IUserMapper</p>
 * <p>Description: userMapper</p>
 * <p>Author: yang</p>
 * <p>Date: 2019年7月31日</p>
 */
@Repository
@Mapper
public interface IUserMapper extends BaseMapper<User> {
    
    /**
     * 
     * <p>Description: 根据用户名获取User</p>
     * @param user 用户
     * @return User 用户
     */
    User getUserByLoginName(User user);
    
    /**
     * 
     * <p>Description: 根据用户名获取User</p>
     * @param user 用户
     * @return User UserToken
     */
    UserToken getUserByUserName(UserToken user);
    
}
