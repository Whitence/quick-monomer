package cn.edu.szu.cs.quickmonomer.util.cache.handler.redis;

import cn.edu.szu.cs.quickmonomer.util.cache.handler.ObjectHandler;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RedisObjectHandler implements ObjectHandler {
    /**
     * 默认时间
     */
    private static final long DEFAULT_TIMEOUT = 3600L;
    /**
     * 默认时间单位
     */
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;

    public RedisObjectHandler(ValueOperations<Object, Object> valueOperations, ObjectHandler nextHandler) {
        this.valueOperations = valueOperations;
        this.nextHandler = nextHandler;
    }

    /**
     * valueOps
     */
    private ValueOperations<Object, Object> valueOperations;

    private ObjectHandler nextHandler;

    @Override
    public Object getObj(String key) {
        return valueOperations.get(key);
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier) {
        return getObjOrNext(key,supplier,DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT);
    }

    @Override
    public Object getObjOrNext(String key, Supplier supplier, long timeout, TimeUnit timeUnit) {
        Object value = this.valueOperations.get(key);
        if(Objects.isNull(value)){
            value = nextHandler.getObjOrNext(key, supplier);
            this.setObj(key,value,timeout,timeUnit);
        }
        return value;
    }

    @Override
    public void setObj(String key, Object value) {
        this.setObj(key,value,DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT);
    }

    @Override
    public void setObj(String key, Object value, long timeout, TimeUnit timeUnit) {
        valueOperations.set(key,value,timeout,timeUnit);
    }

    @Override
    public void setObjWithNext(String key, Object value) {
        this.setObjWithNext(key,value,DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT);
    }

    @Override
    public void setObjWithNext(String key, Object value, long timeout, TimeUnit timeUnit) {
        setObj(key,value,timeout,timeUnit);
        nextHandler.setObjWithNext(key,value,timeout,timeUnit);
    }
}
