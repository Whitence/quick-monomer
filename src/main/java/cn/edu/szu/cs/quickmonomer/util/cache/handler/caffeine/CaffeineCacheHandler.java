package cn.edu.szu.cs.quickmonomer.util.cache.handler.caffeine;


import cn.edu.szu.cs.quickmonomer.util.cache.handler.*;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;


import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @description: 咖啡因处理器
 * @author whitence
 * @date 2023/2/27 22:35
 * @version 1.0
 */
public class CaffeineCacheHandler implements CacheHandler {

    /**
     * 默认时间
     */
    private static final long DEFAULT_TIMEOUT = 60L;
    /**
     * 默认时间单位
     */
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;

    /**
     * 最大键值数量
     */
    private static final long MAXIMUM_SIZE = 512;
    /**
     * 下级处理器
     */
    private CacheHandler handler;

    private Cache<String,Object> caffeine = null;

    public CaffeineCacheHandler() {
        caffeine = Caffeine
                .newBuilder()
                .expireAfterWrite(DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT)
                .maximumSize(MAXIMUM_SIZE)
                .build();
    }

    public CaffeineCacheHandler(CacheHandler handler) {
        caffeine = Caffeine
                .newBuilder()
                .expireAfterWrite(DEFAULT_TIMEOUT,DEFAULT_TIMEUNIT)
                .maximumSize(MAXIMUM_SIZE)
                .build();

        this.handler = handler;
    }

    public CaffeineCacheHandler(CacheHandler handler, Cache<String, Object> caffeine) {
        this.handler = handler;
        this.caffeine = caffeine;
    }

    public void setHandler(CacheHandler cacheHandler){
        this.handler=cacheHandler;
    }

    @Override
    public ObjectHandler value() {
        return new CaffeineObjectHandler(handler.value(),caffeine);
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
        caffeine.invalidate(key);
        return true;
    }

    @Override
    public boolean deleteWithNext(String key) {
        caffeine.invalidate(key);
        handler.delete(key);
        return true;
    }

    @Override
    public boolean deletePattern(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasKey(String key) {
        return !Objects.isNull(caffeine.getIfPresent(key));
    }
}
