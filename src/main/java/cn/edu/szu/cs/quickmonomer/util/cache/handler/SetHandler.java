package cn.edu.szu.cs.quickmonomer.util.cache.handler;



import java.util.Set;

/**
 * @description:
 * @author whitence
 * @date 2023/2/26 20:27
 * @version 1.0
 */

public interface SetHandler {

    /**
     * add
     * @param key
     * @param value
     */
    void add(String key,Object value);

    /**
     * remove
     * @param key
     * @param value
     */
    void remove(String key,Object... value);

    /**
     * isMember
     * @param key
     * @param value
     * @return
     */
    boolean isMember(String key,Object value);

    /**
     * members
     * @param key
     * @return
     */
    Set<Object> members(String key);
    
}
