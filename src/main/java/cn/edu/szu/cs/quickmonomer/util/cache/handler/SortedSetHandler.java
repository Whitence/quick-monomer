package cn.edu.szu.cs.quickmonomer.util.cache.handler;



import cn.hutool.core.lang.Tuple;

/**
 * @description:
 * @author whitence
 * @date 2023/2/26 20:27
 * @version 1.0
 */

public interface SortedSetHandler {

    void add(String key,Object value,double score);

    void addIfAbsent(String key,Object value,double score);

    long count(String key,double min,double max);

    Tuple popMax(String key);

    Tuple popMin(String key);
    
}
