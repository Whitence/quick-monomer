package cn.edu.szu.cs.quickmonomer.util.cache;



import cn.edu.szu.cs.quickmonomer.util.cache.handler.HashHandler;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.ListHandler;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.SetHandler;
import cn.edu.szu.cs.quickmonomer.util.cache.handler.SortedSetHandler;

import java.util.List;
import java.util.function.Supplier;

/**
 * @description: 多级缓存
 * @author whitence
 * @date 2023/2/26 20:54
 * @version 1.0
 */

public interface MultiLevelCache {
    /**
     * 只获取当前级别的数据
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getObj(CacheLevel cacheLevel,String key,Class<T> tClass);

    <T> T getObj(CacheLevel cacheLevel,Class<T> tClass,Object... args);


    <T> List<T> getList(CacheLevel cacheLevel,String key,Class<T> tClass);

    <T> List<T> getList(CacheLevel cacheLevel,Class<T> tClass,Object... args);



    /**
     * 从当前级别开始获取，获取不到去下一级别，返回时同步更新
     * @param key
     * @param supplier
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getObjOrNext(CacheLevel cacheLevel,String key,Supplier<T> supplier,Class<T> tClass);

    <T> T getObjOrNext(CacheLevel cacheLevel, String key, Supplier<T> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval);

    <T> T getObjOrNext(CacheLevel cacheLevel,Supplier<T> supplier,Class<T> tClass,Object... args);

    <T> T getObjOrNext(CacheLevel cacheLevel, Supplier<T> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval, Object... args);



    <T> List<T> getListOrNext(CacheLevel cacheLevel,String key,Supplier<List<T>> supplier,Class<T> tClass);

    <T> List<T> getListOrNext(CacheLevel cacheLevel,String key,Supplier<List<T>> supplier,Class<T> tClass,CacheTimeInterval cacheTimeInterval);

    <T> List<T> getListOrNext(CacheLevel cacheLevel,Supplier<List<T>> supplier,Class<T> tClass,Object... args);

    <T> List<T> getListOrNext(CacheLevel cacheLevel, Supplier<List<T>> supplier, Class<T> tClass, CacheTimeInterval cacheTimeInterval, Object... args);


    /**
     * 为当前级别设置值
     * @param key
     * @param value
     * @param <T>
     */
    <T> void setObj(CacheLevel cacheLevel,String key,T value);

    <T> void setObj(CacheLevel cacheLevel, String key, T value,CacheTimeInterval cacheTimeInterval);

    <T> void setObj(CacheLevel cacheLevel,T value,Object... args);

    <T> void setObj(CacheLevel cacheLevel,T value,CacheTimeInterval cacheTimeInterval,Object... args);


    <T> void setList(CacheLevel cacheLevel,String key,List<T> value);

    <T> void setList(CacheLevel cacheLevel, String key, List<T> value, CacheTimeInterval cacheTimeInterval);

    <T> void setList(CacheLevel cacheLevel,List<T> value,Object... args);

    <T> void setList(CacheLevel cacheLevel,List<T> value,CacheTimeInterval cacheTimeInterval,Object... args);


    /**
     * 为当前级别设置值并且更新到下一个级别
     * @param key
     * @param value
     * @param <T>
     */
    <T> void setObjWithNext(CacheLevel cacheLevel,String key,T value);

    <T> void setObjWithNext(CacheLevel cacheLevel,T value,Object... args);


    <T> void setListWithNext(CacheLevel cacheLevel,String key,List<T> value);

    <T> void setListWithNext(CacheLevel cacheLevel,List<T> value,Object... args);



    /**
     * 删除当前级别的数据
     * @param key
     */
    void delete(CacheLevel cacheLevel,String key);

    void delete(CacheLevel cacheLevel,Object... args);


    /**
     * 删除当前级别的数据以及下一个级别的数据
     * @param key
     */
    void deleteWithNext(CacheLevel cacheLevel,String key);

    void deleteWithNext(CacheLevel cacheLevel,Object... args);

    void deletePattern(CacheLevel cacheLevel,String pattern);


    ListHandler list(CacheLevel cacheLevel);

    HashHandler hash(CacheLevel cacheLevel);

    SortedSetHandler zSet(CacheLevel cacheLevel);

    SetHandler set(CacheLevel cacheLevel);
}
