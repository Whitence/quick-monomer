package cn.edu.szu.cs.quickmonomer.util.cache;

/**
 * @description: TODO
 * @author whitence
 * @date 2023/3/6 16:37
 * @version 1.0
 */
@FunctionalInterface
public interface MultiCacheKeyGenerator {

    String generatorKey(Object... args);

}
