package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.SortedSetHandler;
import cn.hutool.core.lang.Tuple;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisSortedSetHandler implements SortedSetHandler {

    private ZSetOperations<Object, Object> zSetOperations;

    public RedisSortedSetHandler(ZSetOperations<Object, Object> zSetOperations) {
        this.zSetOperations = zSetOperations;
    }

    @Override
    public void add(String key, Object value, double score) {

    }

    @Override
    public void addIfAbsent(String key, Object value, double score) {

    }

    @Override
    public long count(String key, double min, double max) {
        return 0;
    }

    @Override
    public Tuple popMax(String key) {
        return null;
    }

    @Override
    public Tuple popMin(String key) {
        return null;
    }
}
