/**
 * RoleService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gogo.hobby.system.security.entity.Role;
import com.gogo.hobby.system.security.entity.RoleToken;
import java.util.List;

/**
 * 
 * <p>
 * ClassName: IRoleService
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
public interface IRoleService extends IService<Role> {

    /**
     * 
     * <p>
     * Description: 根据用户ID获取角色列表
     * </p>
     * 
     * @param id 用户ID
     * @return List<Role> 角色列表
     */
    public List<Role> getRolesByUserId(Integer id);

    /**
     *
     * <p>
     * Description: 根据用户ID获取角色列表
     * </p>
     *
     * @param id 用户ID
     * @return List<Role> 角色列表
     */
    public List<RoleToken> getListByUserId(Integer id);
}
