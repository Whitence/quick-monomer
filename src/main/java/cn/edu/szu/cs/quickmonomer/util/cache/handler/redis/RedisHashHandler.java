package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.HashHandler;
import org.springframework.data.redis.core.HashOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisHashHandler implements HashHandler {


    private HashOperations<Object, String, Object> opsForHash;

    public RedisHashHandler(HashOperations<Object, String, Object> opsForHash) {
        this.opsForHash=opsForHash;
    }

    @Override
    public void put(String key, String hashKey, Object value) {
        opsForHash.put(key,hashKey,value);
    }

    @Override
    public void putIfAbsent(String key, String hashKey, Object value) {
        opsForHash.putIfAbsent(key,hashKey,value);
    }

    @Override
    public Object get(String key, String hashKey) {
        return opsForHash.get(key,hashKey);
    }

    @Override
    public Set<String> keys(String key) {
        return opsForHash.keys(key);
    }

    @Override
    public List<Object> values(String key) {
        return opsForHash.values(key);
    }

    @Override
    public Map<String, Object> entries(String key) {
        return opsForHash.entries(key);
    }

    @Override
    public void delete(String key, String hashKey) {
        opsForHash.delete(key,hashKey);
    }

    @Override
    public Boolean hasKey(String key, String hashKey) {
        return Boolean.TRUE.equals(opsForHash.hasKey(key,hashKey));
    }
}
