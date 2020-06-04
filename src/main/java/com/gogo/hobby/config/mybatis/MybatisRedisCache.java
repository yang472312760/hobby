/**
 * MybatisRedisCache.java
 * Created at 2019-9-3
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.mybatis;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * ClassName: MybatisRedisCache
 * </p>
 * <p>
 * Description: mybatis二級緩存
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年8月31日
 * </p>
 * @author yang
 */
@Slf4j
@Data
public class MybatisRedisCache implements Cache {

    /**
     * redis模板
     */
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    /** */
    private String id;

    /**
     *
     * <p>
     * Description: TODO
     * </p>
     *
     * @param idd id
     */
    public MybatisRedisCache(final String idd) {
        if (idd == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = idd;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            if (value != null) {
                redisTemplate.opsForValue().set(key.toString(), value);
            }
        } catch (Exception e) {
            log.error("缓存出错 ");
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (key != null) {
                return redisTemplate.opsForValue().get(key.toString());
            }
        } catch (Exception e) {
            log.error("缓存出错 ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
            log.error("缓存出错 ");
        }
        return null;
    }

    @Override
    public void clear() {
        log.debug("清空缓存");
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            log.error("缓存出错 ");
        }
    }

    @Override
    public int getSize() {
        try {
            Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
            return size == null ? 0 : size.intValue();
        } catch (Exception e) {
            log.error("缓存出错 ");
            return 0;
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    /**
     * <p>
     * Description: 设置redisTemplate
     * </p>
     *
     * @param redisTemplate redis模板
     */
    public static void setJedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        MybatisRedisCache.redisTemplate = redisTemplate;
    }

}
