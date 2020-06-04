/**
 * DemoMapper.java
 * Created at 2020-06-04
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.system.demo.mapper;

import java.util.List;

import com.gogo.hobby.config.mybatis.MybatisRedisCache;
import com.gogo.hobby.system.demo.model.Demo;
import com.gogo.hobby.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * ClassName: DemoMapper
 * </p>
 * <p>
 * Description:  Mapper 接口
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2020-06-04
 * </p>
 */
@Repository
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface DemoMapper extends IBaseMapper<Demo> {

}
