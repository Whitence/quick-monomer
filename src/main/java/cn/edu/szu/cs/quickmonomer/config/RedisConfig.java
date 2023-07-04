package cn.edu.szu.cs.quickmonomer.config;

import cn.edu.szu.cs.quickmonomer.util.cache.FastJsonRedisSerializer;
import cn.edu.szu.cs.quickmonomer.util.cache.MultiLevelCache;
import cn.edu.szu.cs.quickmonomer.util.cache.MultiLevelCacheFactory;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @description: redis配置
 * @author whitence
 * @date 2023/4/21 22:20
 * @version 1.0
 */
@Configuration
public class RedisConfig {

    @Bean("myRedisTemplate")
    @Primary
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<Object,Object> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public MultiLevelCache multiLevelCache(@Qualifier("myRedisTemplate") @Autowired RedisTemplate<Object,Object> redisTemplate){
        return MultiLevelCacheFactory.create(redisTemplate);
    }

}
