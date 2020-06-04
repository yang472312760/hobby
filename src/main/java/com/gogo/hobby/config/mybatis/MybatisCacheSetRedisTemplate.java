/**
 * MybatisCacheSetRedisTemplate.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * ClassName: MybatisCacheSetRedisTemplate
 * </p>
 * <p>
 * Description: 给二级缓存注入RedisTemplate
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年8月31日
 * </p>
 *
 * @author yang
 */
@Component
public class MybatisCacheSetRedisTemplate {

    /**
     * redis模板
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 注入RedisTemplate
     */
    @Autowired
    public void setRedisTemplate() {
        MybatisRedisCache.setJedisTemplate(this.redisTemplate);
    }

}
