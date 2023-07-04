package cn.edu.szu.cs.quickmonomer.common.validate;


import cn.edu.szu.cs.quickmonomer.common.api.ResultCode;
import cn.edu.szu.cs.quickmonomer.common.exception.ApiException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Whitence
 * @description: 自定义参数校验
 * @date 2022/10/20 21:41
 */

public class VerifyValidator implements ConstraintValidator<CustomizeValidator,Object> {
    /**
     * 方法所在类
     */
    Class<?>[] predicts;
    /**
     * 方法名
     */
    String[] methodNames;
    /**
     * 是否可空
     */
    boolean nullable;
    @Override
    public void initialize(CustomizeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.predicts = constraintAnnotation.predicts();

        this.methodNames = constraintAnnotation.methodNames();

        this.nullable=constraintAnnotation.nullable();
    }
    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if(predicts.length != methodNames.length){
            throw new ApiException(ResultCode.VALIDATE_FAILED,"校验类和方法数量不一致");
        }

        if(Objects.isNull(o)&&nullable){
            return true;
        }
        if(Objects.isNull(o)){
            throw new ApiException(ResultCode.VALIDATE_FAILED,"参数不能为空");
        }
        for (int i = 0; i < methodNames.length; i++) {
            // 获取方法名
            String methodName = methodNames[i];
            // 获取类字节对象
            Class<?> predict = predicts[i];
            // 获取method对象
            Method method = ReflectUtil.getMethodByName(predict, methodName);
            //
            if(ObjectUtil.isNull(method)){
                throw new ApiException(ResultCode.VALIDATE_FAILED,"第 "+(i+1)+" 个校验方法不存在");
            }
            // 反射获取实例对象
            Object newInstance = ReflectUtil.newInstance(predict);
            // 执行校验方法
            boolean invoke = false;

            try {
                invoke = (boolean) method.invoke(newInstance, o);
            } catch (Exception e) {
                throw new ApiException(ResultCode.VALIDATE_FAILED,e.getMessage());
            }

            if(!invoke){
                return false;
            }

        }
        return true;
    }
}


