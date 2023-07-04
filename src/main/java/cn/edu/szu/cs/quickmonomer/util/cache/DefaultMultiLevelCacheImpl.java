package cn.edu.szu.cs.quickmonomer.util.cache;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.*;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.base.BaseCacheHandler;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.caffeine.CaffeineCacheHandler;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.redis.RedisCacheHandler;
import cn.hutool.core.collection.CollUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author whitence
 * @date 2023/3/5 17:10
 * @version 1.0
 */
class DefaultMultiLevelCacheImpl implements MultiLevelCache {


    private EnumMap<CacheLevel, CacheHandler> handlerEnumMap;

    private MultiCacheKeyGenerator multiCacheKeyGenerator;

    public DefaultMultiLevelCacheImpl(RedisTemplate<Object,Object> redisTemplate) {

        handlerEnumMap=new EnumMap<>(CacheLevel.class);

        // 构造责任链
        BaseCacheHandler baseCacheHandler = new BaseCacheHandler();

        RedisCacheHandler redisCacheHandler = new RedisCacheHandler(baseCacheHandler,redisTemplate);

        CaffeineCacheHandler caffeineCacheHandler = new CaffeineCacheHandler(redisCacheHandler);

        handlerEnumMap.put(CacheLevel.ONE,redisCacheHandler);
        handlerEnumMap.put(CacheLevel.TWO,caffeineCacheHandler);

        multiCacheKeyGenerator=new DefaultMultiCacheKeyGenerator();
    }

    private String generatorKey(Object... args){
        return multiCacheKeyGenerator.generatorKey(args);
    }

    private <T> List<T> obj2List(Object value,Class<T> tClass){

        if(Objects.isNull(value)){
            return Collections.emptyList();
        }

        if(value instanceof List){

            List<?> tmp = (List<?>) value;

            if(CollUtil.isEmpty(tmp)){
                return Collections.emptyList();
            }

            if(tClass.isInstance(tmp.get(0))){
                return (List<T>) tmp;
            }

        }
        throw new ClassCastException("Can not cast obj to list!");

    }

    @Override
    public <T> T getObj(CacheLevel cacheLevel, String key, Class<T> tClass) {
        Object value = handlerEnumMap.get(cacheLevel).value().getObj(key);
        return Objects.isNull(value)?null:tClass.cast(value);
    }

    @Override
    public <T> T getObj(CacheLevel cacheLevel, Class<T> tClass, Object... args) {
        return getObj(cacheLevel,generatorKey(args),tClass);
    }

    @Override
    public <T> List<T> getList(CacheLevel cacheLevel, String key, Class<T> tClass) {
        Object value = handlerEnumMap.get(cacheLevel).value().getObj(key);
        return obj2List(value,tClass);
    }

    @Override
    public <T> List<T> getList(CacheLevel cacheLevel, Class<T> tClass, Object... args) {
        return this.getList(cacheLevel,generatorKey(args),tClass);
    }

    @Override
    public <T> T getObjOrNext(CacheLevel cacheLevel, String key, Supplier<T> supplier, Class<T> tClass) {

        Object value = handlerEnumMap.get(cacheLevel).value().getObjOrNext(key, supplier);

        return Objects.isNull(value)?null:tClass.cast(value);
    }

    @Override
    public <T> T getObjOrNext(CacheLevel cacheLevel, String key, Supplier<T> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval) {
        Object value = handlerEnumMap.get(cacheLevel).value().getObjOrNext(key, supplier, cacheTimeInterval.getTime(), cacheTimeInterval.getTimeUnit());
        return Objects.isNull(value)?null:tClass.cast(value);
    }

    @Override
    public <T> T getObjOrNext(CacheLevel cacheLevel, Supplier<T> supplier, Class<T> tClass, Object... args) {
        return getObjOrNext(cacheLevel,generatorKey(args),supplier,tClass);
    }

    @Override
    public <T> T getObjOrNext(CacheLevel cacheLevel, Supplier<T> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval, Object... args) {
        return getObjOrNext(cacheLevel,generatorKey(args),supplier,tClass,cacheTimeInterval);
    }

    @Override
    public <T> List<T> getListOrNext(CacheLevel cacheLevel, String key, Supplier<List<T>> supplier, Class<T> tClass) {
        return obj2List(handlerEnumMap.get(cacheLevel).value().getObjOrNext(key, supplier),tClass);
    }

    @Override
    public <T> List<T> getListOrNext(CacheLevel cacheLevel, String key, Supplier<List<T>> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval) {
        return obj2List(handlerEnumMap.get(cacheLevel).value().getObjOrNext(key, supplier,cacheTimeInterval.getTime(),cacheTimeInterval.getTimeUnit()),tClass);
    }

    @Override
    public <T> List<T> getListOrNext(CacheLevel cacheLevel, Supplier<List<T>> supplier, Class<T> tClass, Object... args) {
        return getListOrNext(cacheLevel,generatorKey(args),supplier,tClass);
    }

    @Override
    public <T> List<T> getListOrNext(CacheLevel cacheLevel, Supplier<List<T>> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval, Object... args) {
        return getListOrNext(cacheLevel,generatorKey(args),supplier,tClass,cacheTimeInterval);
    }

    @Override
    public <T> void setObj(CacheLevel cacheLevel, String key, T value) {
        handlerEnumMap.get(cacheLevel).value().setObj(key,value);
    }

    @Override
    public <T> void setObj(CacheLevel cacheLevel, String key, T value, CacheTimeInterval cacheTimeInterval) {
        handlerEnumMap.get(cacheLevel).value().setObj(key,value, cacheTimeInterval.getTime(), cacheTimeInterval.getTimeUnit());
    }

    @Override
    public <T> void setObj(CacheLevel cacheLevel, T value, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObj(generatorKey(args),value);
    }

    @Override
    public <T> void setObj(CacheLevel cacheLevel, T value, CacheTimeInterval cacheTimeInterval, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObj(generatorKey(args),value, cacheTimeInterval.getTime(), cacheTimeInterval.getTimeUnit());
    }

    @Override
    public <T> void setList(CacheLevel cacheLevel, String key, List<T> value) {
        handlerEnumMap.get(cacheLevel).value().setObj(key,value);
    }

    @Override
    public <T> void setList(CacheLevel cacheLevel, String key, List<T> value, CacheTimeInterval cacheTimeInterval) {
        handlerEnumMap.get(cacheLevel).value().setObj(key,value, cacheTimeInterval.getTime(), cacheTimeInterval.getTimeUnit());
    }

    @Override
    public <T> void setList(CacheLevel cacheLevel, List<T> value, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObj(generatorKey(args),value);
    }

    @Override
    public <T> void setList(CacheLevel cacheLevel, List<T> value, CacheTimeInterval cacheTimeInterval, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObj(generatorKey(args),value, cacheTimeInterval.getTime(), cacheTimeInterval.getTimeUnit());
    }

    @Override
    public <T> void setObjWithNext(CacheLevel cacheLevel, String key, T value) {
        handlerEnumMap.get(cacheLevel).value().setObjWithNext(key,value);
    }

    @Override
    public <T> void setObjWithNext(CacheLevel cacheLevel, T value, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObjWithNext(generatorKey(args),value);
    }

    @Override
    public <T> void setListWithNext(CacheLevel cacheLevel, String key, List<T> value) {
        handlerEnumMap.get(cacheLevel).value().setObjWithNext(key,value);
    }

    @Override
    public <T> void setListWithNext(CacheLevel cacheLevel, List<T> value, Object... args) {
        handlerEnumMap.get(cacheLevel).value().setObjWithNext(generatorKey(args),value);
    }

    @Override
    public void delete(CacheLevel cacheLevel, String key) {
        handlerEnumMap.get(cacheLevel).delete(key);
    }

    @Override
    public void delete(CacheLevel cacheLevel, Object... args) {
        handlerEnumMap.get(cacheLevel).delete(generatorKey(args));
    }

    @Override
    public void deleteWithNext(CacheLevel cacheLevel, String key) {
        handlerEnumMap.get(cacheLevel).deleteWithNext(key);
    }

    @Override
    public void deleteWithNext(CacheLevel cacheLevel, Object... args) {
        handlerEnumMap.get(cacheLevel).deleteWithNext(generatorKey(args));
    }

    @Override
    public void deletePattern(CacheLevel cacheLevel, String pattern) {
        handlerEnumMap.get(cacheLevel).deletePattern(pattern);
    }

    @Override
    public ListHandler list(CacheLevel cacheLevel) {
        return handlerEnumMap.get(cacheLevel).list();
    }

    @Override
    public HashHandler hash(CacheLevel cacheLevel) {
        return handlerEnumMap.get(cacheLevel).hash();
    }

    @Override
    public SortedSetHandler zSet(CacheLevel cacheLevel) {
        return handlerEnumMap.get(cacheLevel).zSet();
    }

    @Override
    public SetHandler set(CacheLevel cacheLevel) {
        return handlerEnumMap.get(cacheLevel).set();
    }


}
