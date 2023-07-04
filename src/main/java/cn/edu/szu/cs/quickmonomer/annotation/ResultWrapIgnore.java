package cn.edu.szu.cs.quickmonomer.annotation;

import java.lang.annotation.*;

/**
 * @description: 结果包装排除注解
 * @author whitence
 * @date 2023/4/22 14:45
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultWrapIgnore {
}
