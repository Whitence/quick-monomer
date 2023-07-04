package cn.edu.szu.cs.quickmonomer.util.cache.handler.base;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.ObjectHandler;
import cn.hutool.log.StaticLog;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/21 18:48
 * @version 1.0
 */
public class BaseObjectHandler implements ObjectHandler {
    @Override
    public Object getObj(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier) {

        return supplier.get();
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier, long timeout, TimeUnit timeUnit) {
        return supplier.get();
    }

    @Override
    public void setObj(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObj(String key, Object value, long timeout, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObjWithNext(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObjWithNext(String key, Object value, long timeout, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
}
