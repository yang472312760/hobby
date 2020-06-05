/**
 * RoleMapper.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gogo.hobby.system.security.entity.Role;
import com.gogo.hobby.system.security.entity.RoleToken;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * <p>
 * ClassName: IRoleMapper
 * </p>
 * <p>
 * Description: 角色Mapper
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月31日
 * </p>
 */
@Repository
@Mapper
public interface IRoleMapper extends BaseMapper<Role> {
    
    /**
     * 
     * <p>Description: 根据用户ID获取角色列表</p>
     * @param id 用户ID
     * @return List<Role> 角色列表
     */
    List<Role> getRolesByUserTokenId(Integer id);
    
    /**
     * 
     * <p>Description: 根据用户ID获取角色列表</p>
     * @param id 用户ID
     * @return List<RoleToken> 角色列表
     */
    List<RoleToken> getListByUserTokenId(Integer id);

}
