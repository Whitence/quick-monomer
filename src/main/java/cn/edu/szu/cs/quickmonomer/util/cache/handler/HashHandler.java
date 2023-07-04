package cn.edu.szu.cs.quickmonomer.util.cache.handler;



import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author whitence
 * @date 2023/2/26 20:27
 * @version 1.0
 */

public interface HashHandler {
    /**
     * put
     * @param key
     * @param hashKey
     * @param value
     */
    void put(String key,String hashKey,Object value);

    /**
     * putIfAbsent
     * @param key
     * @param hashKey
     * @param value
     */
    void putIfAbsent(String key,String hashKey,Object value);

    /**
     * get
     * @param key
     * @param hashKey
     * @return
     */
    Object get(String key,String hashKey);

    /**
     * keys
     * @param key
     * @return
     */
    Set<String> keys(String key);

    /**
     * values
     * @param key
     * @return
     */
    List<Object> values(String key);

    /**
     * entries
     * @param key
     * @return
     */
    Map<String,Object> entries(String key);

    /**
     * delete
     * @param key
     * @param hashKey
     */
    void delete(String key,String hashKey);

    /**
     * hasKey
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hasKey(String key,String hashKey);
    
}
