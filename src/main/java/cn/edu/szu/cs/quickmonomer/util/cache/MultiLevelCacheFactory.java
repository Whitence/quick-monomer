package cn.edu.szu.cs.quickmonomer.util.cache;


import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;


/**
 * @description: 缓存处理器抽象工厂
 * @author whitence
 * @date 2023/3/5 16:48
 * @version 1.0
 */
public class MultiLevelCacheFactory {

    //private static EnumMap<CacheLevel, CacheHandlerFactory> cacheHandlerFactoryEnumMap;
    //
    //static {
    //
    //    cacheHandlerFactoryEnumMap=new EnumMap<>(CacheLevel.class);
    //    //
    //    cacheHandlerFactoryEnumMap.put(CacheLevel.NONE,new DefaultBaseCacheHandlerFactory());
    //    // 默认一级为redis
    //    cacheHandlerFactoryEnumMap.put(CacheLevel.ONE,new RedisCacheHandlerFactory());
    //    // 默认二级为咖啡因
    //    cacheHandlerFactoryEnumMap.put(CacheLevel.TWO,new CaffeineCacheHandlerFactory());
    //
    //
    //}

    private static volatile MultiLevelCache multiLevelCache = null;

    public static MultiLevelCache create(RedisTemplate<Object,Object> redisTemplate){
        if(Objects.isNull(multiLevelCache)){
            synchronized (MultiLevelCacheFactory.class){
                if(Objects.isNull(multiLevelCache)){
                    multiLevelCache=new DefaultMultiLevelCacheImpl(redisTemplate);
                }
            }
        }
        return multiLevelCache;
    }



}
