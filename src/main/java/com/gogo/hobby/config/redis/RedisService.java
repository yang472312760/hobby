/**
 * RedisService.java
 * Created at 2019-6-18
 * Created by yang
 * Copyright (C) 2019 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.gogo.hobby.config.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * ClassName: RedisService
 * </p>
 * <p>
 * Description: redis工具类
 * </p>
 * <p>
 * Author: yang
 * </p>
 * <p>
 * Date: 2019年7月11日
 * </p>
 *
 * @author yang
 */
@Slf4j
@Component
public final class RedisService {

    /**
     * redis操作类
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisKeyGenerator redisKeyGenerator;

    /**
     * <p>
     * Description: 指定缓存失效时间
     * </p>
     *
     * @param key  键
     * @param time 时间(秒)
     * @return boolean 是否成功
     */
    public Boolean expire(String key, long time) {
        if (time > 0) {
            return this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * <p>
     * Description: 指定缓存失效时间
     * </p>
     *
     * @param key  键
     * @param time 时间(秒)
     * @param unit 时间单位
     * @return boolean 是否成功
     */
    public Boolean expire(String key, long time, final TimeUnit unit) {
        if (time > 0) {
            return this.redisTemplate.expire(key, time, unit);
        }
        return false;
    }

    /**
     * <p>
     * Description: 根据key 获取过期时间
     * </p>
     *
     * @param key 键 不能为null
     * @return long 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * <p>
     * Description: 判断key是否存在
     * </p>
     *
     * @param key key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return this.redisTemplate.hasKey(key);
    }

    /**
     * <p>
     * Description: 删除缓存
     * </p>
     *
     * @param key key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * <p>
     * Description: 普通缓存获取
     * </p>
     *
     * @param key 键
     * @return Object 值
     */
    public Object get(String key) {
        Object value = key == null ? null : getOpsForValue().get(key);
        return value;
    }

    /**
     * <p>
     * Description: 普通缓存放入
     * </p>
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        getOpsForValue().set(key, value);
    }

    /**
     * <p>
     * Description: 普通缓存放入并设置时间
     * </p>
     *
     * @param key   键
     * @param value 值
     * @param time  time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            getOpsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * <p>
     * Description: 键值操作器
     * </p>
     *
     * @return ValueOperations
     */
    private ValueOperations<String, Object> getOpsForValue() {
        return this.redisTemplate.opsForValue();
    }

    /**
     * <p>
     * Description: 递增
     * </p>
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return long
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        Long value = getOpsForValue().increment(key, delta);
        return value == null ? 0 : value;
    }

    /**
     * <p>
     * Description: 递减
     * </p>
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return long
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        Long value = getOpsForValue().increment(key, -delta);
        return value == null ? 0 : value;
    }

    /**
     * <p>
     * Description: HashGet
     * </p>
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return java.lang.Object
     */
    public Object hget(String key, String item) {
        return getOpsForHash().get(key, item);
    }

    /**
     * <p>
     * Description: 获取hashKey对应的所有键值
     * </p>
     *
     * @param key 键
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     */
    public Map<Object, Object> hmget(String key) {
        return getOpsForHash().entries(key);
    }

    /**
     * <p>
     * Description: HashSet
     * </p>
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public void hmset(String key, Map<String, Object> map) {
        getOpsForHash().putAll(key, map);
    }

    /**
     * <p>
     * Description: HashSet 并设置时间
     * </p>
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public void hmset(String key, Map<String, Object> map, long time) {
        getOpsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * <p>
     * Description: 向一张hash表中放入数据,如果不存在将创建
     * </p>
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hset(String key, String item, Object value) {
        getOpsForHash().put(key, item, value);
    }

    /**
     * <p>
     * Description: 向一张hash表中放入数据,如果不存在将创建
     * </p>
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public void hset(String key, String item, Object value, long time) {
        getOpsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * <p>
     * Description: 删除hash表中的值
     * </p>
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        getOpsForHash().delete(key, item);
    }

    /**
     * <p>
     * Description: 判断hash表中是否有该项的值
     * </p>
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return boolean 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return getOpsForHash().hasKey(key, item);
    }

    /**
     * <p>
     * Description: hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * </p>
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return double
     */
    public double hincr(String key, String item, double by) {
        return getOpsForHash().increment(key, item, by);
    }

    /**
     * <p>
     * Description: hash递减
     * </p>
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return double
     */
    public double hdecr(String key, String item, double by) {
        return getOpsForHash().increment(key, item, -by);
    }

    /**
     * <p>
     * Description: hash操作器
     * </p>
     *
     * @return HashOperations
     */
    private HashOperations<String, Object, Object> getOpsForHash() {
        return this.redisTemplate.opsForHash();
    }

    /**
     * <p>
     * Description: 根据key获取Set中的所有值
     * </p>
     *
     * @param key 键
     * @return java.util.Set<java.lang.Object>
     */
    public Set<Object> sGet(String key) {
        try {
            return getOpsForSet().members(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <p>
     * Description: 根据value从一个set中查询,是否存在
     * </p>
     *
     * @param key   键
     * @param value 值
     * @return boolean true 存在 false不存在
     */
    public Boolean sHasKey(String key, Object value) {
        return getOpsForSet().isMember(key, value);
    }

    /**
     * <p>
     * Description: 将数据放入set缓存
     * </p>
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return long 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            Long value = getOpsForSet().add(key, values);
            return value == null ? 0 : value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>
     * Description: set操作器
     * </p>
     *
     * @return SetOperations
     */
    private SetOperations<String, Object> getOpsForSet() {
        return this.redisTemplate.opsForSet();
    }

    /**
     * <p>
     * Description: 将set数据放入缓存
     * </p>
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return long 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = getOpsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count == null ? 0 : count;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>
     * Description: 获取set缓存的长度
     * </p>
     *
     * @param key 键
     * @return long
     */
    public long sGetSetSize(String key) {
        try {
            Long value = getOpsForSet().size(key);
            return value == null ? 0 : value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>
     * Description: 移除值为value的
     * </p>
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return long 移除的个数
     */
    public long setRemove(String key, Object... values) {

        try {
            Long count = getOpsForSet().remove(key, values);
            return count == null ? 0 : count;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>
     * Description: 获取list缓存的内容
     * </p>
     *
     * @param key   键
     * @param start 开始
     * @param end   end 结束 0 到 -1代表所有值
     * @return List<Object>
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return getOpsForList().range(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <p>
     * Description: 获取list缓存的长度
     * </p>
     *
     * @param key 键
     * @return long
     */
    public long lGetListSize(String key) {
        try {
            Long value = getOpsForList().size(key);
            return value == null ? 0 : value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>
     * Description: 通过索引 获取list中的值
     * </p>
     *
     * @param key   键
     * @param index 索引
     * @return Object
     */
    public Object lGetIndex(String key, long index) {
        try {
            return getOpsForList().index(key, index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <p>
     * Description: 将list放入缓存
     * </p>
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public Long lSet(String key, Object value) {
        return getOpsForList().rightPush(key, value);
    }

    /**
     * <p>
     * Description: 将list放入缓存
     * </p>
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void lSet(String key, Object value, long time) {
        getOpsForList().rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * <p>
     * Description: 将list放入缓存
     * </p>
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public Long lSet(String key, List<Object> value) {
        return getOpsForList().rightPushAll(key, value);
    }

    /**
     * <p>
     * Description: 将list放入缓存
     * </p>
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void lSet(String key, List<Object> value, long time) {
        getOpsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * <p>
     * Description: list操作器
     * </p>
     *
     * @return ListOperations
     */
    private ListOperations<String, Object> getOpsForList() {
        return this.redisTemplate.opsForList();
    }

    /**
     * <p>
     * Description: 根据索引修改list中的某条数据
     * </p>
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lUpdateIndex(String key, long index, Object value) {
        getOpsForList().set(key, index, value);
    }

    /**
     * <p>
     * Description: 移除N个值为value
     * </p>
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return long 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = getOpsForList().remove(key, count, value);
            return remove == null ? 0 : remove;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * <p>Description: TODO</p>
     *
     * @param keyPrefix
     * @param params
     * @return
     */
    public String initRedisKeyByParams(String keyPrefix, Object[] params) {
        final StringBuilder STRING_BUILDER = new StringBuilder();
        STRING_BUILDER.append(keyPrefix);
        redisKeyGenerator.handleParams(STRING_BUILDER, params);
        String redisKey = STRING_BUILDER.toString();
        return redisKey;
    }

    /**
     * <p>Description: TODO</p>
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * <p>
     * Description: 删除缓存
     * </p>
     *
     * @param key key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String pattern) {
        Set<String> keys = keys(pattern);
        if (keys != null && keys.size() > 0) {
            if (keys.size() == 1) {
                this.redisTemplate.delete((String) keys.toArray()[0]);
            } else {
                this.redisTemplate.delete(CollectionUtils.arrayToList(keys.toArray()));
            }
        }
    }

}
