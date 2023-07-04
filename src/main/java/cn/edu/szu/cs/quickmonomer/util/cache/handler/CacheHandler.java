package cn.edu.szu.cs.quickmonomer.util.cache.handler;
/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/21 18:47
 * @version 1.0
 */
public interface CacheHandler {

    ObjectHandler value();

    ListHandler list();

    HashHandler hash();

    SetHandler set();

    SortedSetHandler zSet();

    boolean delete(String key);

    boolean deleteWithNext(String key);

    boolean deletePattern(String key);

    boolean hasKey(String key);
}
