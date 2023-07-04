package cn.edu.szu.cs.quickmonomer.util.cache.handler;



import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @description:
 * @author whitence
 * @date 2023/2/26 20:27
 * @version 1.0
 */

public interface ListHandler {
    /**
     * get directly
     * @param key
     * @return
     */
    List<Object> get(String key);

    /**
     * set list
     * @param key
     * @param list
     */
    void set(String key,Collection<Object> list);


    /**
     * set list
     * @param key
     * @param supplier
     */
    void set(String key,Supplier<? extends Collection<Object>> supplier);


    /**
     * leftPush
     * @param key
     * @param value
     */
    void leftPush(String key,Object value);

    /**
     * leftPushAll
     * @param key
     * @param values
     */
    void leftPushAll(String key, Collection<Object> values);

    /**
     * rightPop
     * @param key
     * @return
     */
    Object rightPop(String key);

    /**
     * rightPush
     * @param key
     * @param value
     */
    void rightPush(String key,Object value);

    /**
     * rightPushAll
     * @param key
     * @param values
     */
    void rightPushAll(String key, Collection<Object> values);

    /**
     * leftPop
     * @param key
     * @return
     */
    Object leftPop(String key);
    
}
