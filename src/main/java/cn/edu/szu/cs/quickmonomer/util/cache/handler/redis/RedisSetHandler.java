package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.SetHandler;
import org.springframework.data.redis.core.SetOperations;

import java.util.Set;

public class RedisSetHandler implements SetHandler {

    private SetOperations<Object, Object> setOperations;

    public RedisSetHandler(SetOperations<Object, Object> objectObjectSetOperations) {
        this.setOperations = objectObjectSetOperations;
    }

    @Override
    public void add(String key, Object value) {
        setOperations.add(key,value);
    }

    @Override
    public void remove(String key, Object... value) {
        setOperations.remove(key,value);
    }

    @Override
    public boolean isMember(String key, Object value) {
        return Boolean.TRUE.equals(setOperations.isMember(key,value));
    }

    @Override
    public Set<Object> members(String key) {
        return setOperations.members(key);
    }
}
