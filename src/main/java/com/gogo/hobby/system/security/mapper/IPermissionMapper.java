/**
 * PermissionMapper.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.mapper;

import com.gogo.hobby.system.security.entity.Permission;
import com.gogo.hobby.system.security.entity.PermissionToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * <p>ClassName: IPermissionMapper</p>
 * <p>Description: 许可Mapper</p>
 * <p>Author: yang</p>
 * <p>Date: 2019年7月31日</p>
 */
@Repository
@Mapper
public interface IPermissionMapper extends BaseMapper<Permission> {
    
    /**
     * 
     * <p>Description: 根据角色获取许可列表</p>
     * @param roleId 角色ID
     * @return List<Permission> 许可列表
     */
    List<Permission> getListByRoleId(Integer roleId);
    
    /**
     * 
     * <p>Description: 根据角色获取许可列表</p>
     * @param roleId 角色ID
     * @return List<PermissionToken> 许可列表
     */
    List<PermissionToken> getPermissionsByRoleId(Integer roleId);

}
