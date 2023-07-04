package cn.edu.szu.cs.quickmonomer.common.validate;




import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @description: 自定义校验
 * @author hjc
 * @date 2023/3/14 15:59
 * @version 1.0
 */

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerifyValidator.class})
public @interface CustomizeValidator {

    // 错误提示
    String message() default "";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

    // 校验方法名
    String[] methodNames() default {};

    // 校验方法所在类
    Class<?>[] predicts() default {};

    // 是否可空
    boolean nullable() default false;
}
