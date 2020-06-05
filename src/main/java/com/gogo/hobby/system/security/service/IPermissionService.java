/**
 * PermissionService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gogo.hobby.system.security.entity.Permission;
import com.gogo.hobby.system.security.entity.PermissionToken;
import java.util.List;

/**
 * 
 * <p>
 * ClassName: IPermissionService
 * </p>
 * <p>
 * Description: 许可服务类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 
     * <p>
     * Description: 根据角色ID获取许可列表
     * </p>
     * 
     * @param roleId 角色ID
     * @return List<Permission> 许可列表
     */
    List<Permission> getListByRoleId(Integer roleId);

    /**
     * 
     * <p>
     * Description: 根据角色ID获取许可列表
     * </p>
     * 
     * @param roleId 角色ID
     * @return List<PermissionToken> 许可列表
     */
    List<PermissionToken> getPermissionsByRoleId(Integer roleId);

}
