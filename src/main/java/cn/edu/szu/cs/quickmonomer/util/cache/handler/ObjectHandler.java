package cn.edu.szu.cs.quickmonomer.util.cache.handler;



import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @description:
 * @author whitence
 * @date 2023/2/26 20:27
 * @version 1.0
 */

public interface ObjectHandler {
    /**
     * get directly
     * @param key
     * @return
     */
    Object getObj(String key);

    /**
     * get or get from next handler
     * @param key
     * @param supplier
     * @return
     */
    Object getObjOrNext(String key,Supplier supplier);

    /**
     * get or get from next handler
     * @param key
     * @param supplier
     * @param timeout
     * @param timeUnit
     * @return
     */
    Object getObjOrNext(String key,Supplier supplier,long timeout,TimeUnit timeUnit);

    /**
     * set directly
     * @param key
     * @param value
     */
    void setObj(String key,Object value);

    /**
     * set directly with time
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    void setObj(String key,Object value,long timeout,TimeUnit timeUnit);

    /**
     * set while update the next level
     * @param key
     * @param value
     */
    void setObjWithNext(String key,Object value);

    /**
     * set while update the next level
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    void setObjWithNext(String key,Object value,long timeout,TimeUnit timeUnit);



    
}
