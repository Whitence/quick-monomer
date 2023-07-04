package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;


import cn.edu.szu.cs.quickmonomer.util.cache.handler.*;
import cn.hutool.core.collection.CollUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @description: redis缓存
 * @author whitence
 * @date 2023/2/26 23:54
 * @version 1.0
 */
public class RedisCacheHandler implements CacheHandler {
    /**
     * 下级处理器
     */
    private CacheHandler handler;

    private RedisTemplate<Object,Object> redisTemplate;

    public RedisCacheHandler(CacheHandler handler, RedisTemplate<Object, Object> redisTemplate) {
        this.handler = handler;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ObjectHandler value() {
        return new RedisObjectHandler(redisTemplate.opsForValue(), handler.value());
    }

    @Override
    public ListHandler list() {
        return new RedisListHandler(redisTemplate.opsForList());
    }

    @Override
    public HashHandler hash() {
        return new RedisHashHandler(redisTemplate.opsForHash());
    }


    @Override
    public SetHandler set() {
        return new RedisSetHandler(redisTemplate.opsForSet());
    }

    @Override
    public SortedSetHandler zSet() {
        return new RedisSortedSetHandler(redisTemplate.opsForZSet());
    }

    @Override
    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public boolean deleteWithNext(String key) {
        return delete(key);
    }

    @Override
    public boolean deletePattern(String key) {
        Set<Object> keys = redisTemplate.keys(key);
        if(CollUtil.isEmpty(keys)){
            return false;
        }
        redisTemplate.delete(keys);
        return true;
    }

    @Override
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }


}
