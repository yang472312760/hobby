/**
 * PermissionServiceImpl.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gogo.hobby.system.security.entity.Permission;
import com.gogo.hobby.system.security.entity.PermissionToken;
import com.gogo.hobby.system.security.mapper.IPermissionMapper;
import com.gogo.hobby.system.security.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>ClassName: PermissionServiceImpl</p>
 * <p>Description: 许可服务类</p>
 * <p>Author: yang</p>
 * <p>Date: 2019年7月31日</p>
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<IPermissionMapper, Permission> implements IPermissionService {
    
    /**
     * 许可接口
     */
    @Autowired private IPermissionMapper permissionMapper;

    @Override
    public List<Permission> getListByRoleId(Integer roleId) {
        return this.permissionMapper.getListByRoleId(roleId);
    }

    @Override
    public List<PermissionToken> getPermissionsByRoleId(Integer roleId) {
        return this.permissionMapper.getPermissionsByRoleId(roleId);
    }

}
