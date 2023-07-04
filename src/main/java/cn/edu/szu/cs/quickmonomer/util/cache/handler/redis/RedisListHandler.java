package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.ListHandler;
import org.springframework.data.redis.core.ListOperations;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/21 15:11
 * @version 1.0
 */
public class RedisListHandler implements ListHandler {

    private  ListOperations<Object, Object> opsForList;

    public RedisListHandler(ListOperations<Object, Object> opsForList) {
        this.opsForList = opsForList;
    }

    @Override
    public List<Object> get(String key) {
        return opsForList.range(key,0,-1);
    }


    @Override
    public void set(String key, Collection<Object> list) {
        opsForList.rightPushAll(key,list);
    }

    @Override
    public void set(String key, Supplier<? extends Collection<Object>> supplier) {
        opsForList.rightPushAll(key,supplier.get());
    }

    @Override
    public void leftPush(String key, Object value) {
        opsForList.leftPush(key,value);
    }

    @Override
    public void leftPushAll(String key, Collection<Object> values) {
        opsForList.leftPushAll(key,values);
    }

    @Override
    public Object rightPop(String key) {
        return opsForList.rightPop(key);
    }

    @Override
    public void rightPush(String key, Object value) {
        opsForList.rightPush(key,value);
    }

    @Override
    public void rightPushAll(String key, Collection<Object> values) {
        opsForList.rightPushAll(key,values);
    }

    @Override
    public Object leftPop(String key) {
        return opsForList.leftPop(key);
    }
}
