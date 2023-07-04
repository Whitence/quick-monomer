package cn.edu.szu.cs.quickmonomer.util.cache;


import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;


/**
 * @description: TODO
 * @author whitence
 * @date 2023/3/6 16:37
 * @version 1.0
 */

public class DefaultMultiCacheKeyGenerator implements MultiCacheKeyGenerator {

    private static final String DEFAULT_PREFIX="BAJIE";

    @Override
    public String generatorKey(Object... args) {

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];

        String className = stackTraceElement.getClassName();
        String res= null;
        try {

            Class<?> aClass = Class.forName(className);

            String value = aClass.getSimpleName();

            String methodName = stackTraceElement.getMethodName();

            res = String.join(":",
                    DEFAULT_PREFIX,
                    value.toUpperCase(Locale.ROOT),
                    methodName.toUpperCase(Locale.ROOT),
                    Arrays.stream(args).map(Object::toString).collect(Collectors.joining("_"))
            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

}
