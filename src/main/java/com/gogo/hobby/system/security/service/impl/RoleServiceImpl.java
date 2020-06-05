/**
 * RoleServiceImpl.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashSet;
import java.util.List;

import com.gogo.hobby.system.security.entity.Role;
import com.gogo.hobby.system.security.entity.RoleToken;
import com.gogo.hobby.system.security.mapper.IRoleMapper;
import com.gogo.hobby.system.security.service.IPermissionService;
import com.gogo.hobby.system.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>
 * ClassName: RoleServiceImpl
 * </p>
 * <p>
 * Description: 角色服务类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, Role> implements IRoleService {

    /**
     * 许可服务类
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 角色接口
     */
    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(Integer id) {
        List<Role> roles = this.roleMapper.getRolesByUserTokenId(id);
        for (Role role : roles) {
            role.setPermissions(new HashSet<>(this.permissionService.getListByRoleId(role.getId())));
        }
        return roles;
    }

    @Override
    public List<RoleToken> getListByUserId(Integer id) {
        List<RoleToken> roles = this.roleMapper.getListByUserTokenId(id);
        for (RoleToken role : roles) {
            role.setPermissions(new HashSet<>(this.permissionService.getPermissionsByRoleId(role.getId())));
        }
        return roles;
    }
}
