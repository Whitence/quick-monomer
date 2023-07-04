package cn.edu.szu.cs.quickmonomer.util.cache.handler.caffeine;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.ObjectHandler;
import com.github.benmanes.caffeine.cache.Cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/21 16:14
 * @version 1.0
 */
public class CaffeineObjectHandler implements ObjectHandler {


    private ObjectHandler objectHandler;

    private Cache<String,Object> caffeine = null;

    public CaffeineObjectHandler(ObjectHandler objectHandler, Cache<String, Object> caffeine) {
        this.objectHandler = objectHandler;
        this.caffeine = caffeine;
    }

    @Override
    public Object getObj(String key) {
        return caffeine.getIfPresent(key);
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier) {

        Object value = caffeine.getIfPresent(key);
        if(Objects.isNull(value)){
            value=objectHandler.getObjOrNext(key,supplier);
            caffeine.put(key,value);
        }
        return value;
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier, long timeout, TimeUnit timeUnit) {
        Object value = caffeine.getIfPresent(key);
        if(Objects.isNull(value)){
            value=objectHandler.getObjOrNext(key,supplier,timeout,timeUnit);
            caffeine.put(key,value);
        }
        return value;
    }

    @Override
    public void setObj(String key, Object value) {
        caffeine.put(key,value);
    }

    @Override
    public void setObj(String key, Object value, long timeout, TimeUnit timeUnit) {
        caffeine.put(key,value);
    }

    @Override
    public void setObjWithNext(String key, Object value) {
        caffeine.put(key,value);
        objectHandler.setObjWithNext(key, value);
    }

    @Override
    public void setObjWithNext(String key, Object value, long timeout, TimeUnit timeUnit) {
        caffeine.put(key,value);
        objectHandler.setObjWithNext(key, value, timeout, timeUnit);
    }
}
