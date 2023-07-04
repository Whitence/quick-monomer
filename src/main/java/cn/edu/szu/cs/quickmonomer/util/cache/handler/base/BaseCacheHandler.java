package cn.edu.szu.cs.quickmonomer.util.cache.handler.base;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.*;

/**
 * @description: 基本处理器
 * @author whitence
 * @date 2023/2/27 21:57
 * @version 1.0
 */
public class BaseCacheHandler implements CacheHandler {


    @Override
    public ObjectHandler value() {
        return new BaseObjectHandler();
    }

    @Override
    public ListHandler list() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashHandler hash() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SetHandler set() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedSetHandler zSet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(String key) {
        return true;
    }

    @Override
    public boolean deleteWithNext(String key) {
        return false;
    }

    @Override
    public boolean deletePattern(String key) {
        return false;
    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }
}
