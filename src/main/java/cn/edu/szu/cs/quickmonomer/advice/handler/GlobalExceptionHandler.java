package cn.edu.szu.cs.quickmonomer.advice.handler;


import cn.edu.szu.cs.quickmonomer.common.api.Result;
import cn.edu.szu.cs.quickmonomer.common.api.ResultCode;
import cn.edu.szu.cs.quickmonomer.common.exception.ApiException;
import cn.hutool.core.text.CharSequenceUtil;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


/**
 * @description: 全局异常处理
 * @author whitence
 * @date 2023/3/14 23:57
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 顶级异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.failed(e.getMessage());
    }

    /**
     * 处理接口问题
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApiException.class)
    public Result<String> handleApiException(ApiException e) {
        if (e.getErrorCode() != null) {
            return Result.failed(e.getErrorCode(), e.getMessage());
        }
        return Result.failed(e.getMessage());
    }


    /**
     * 处理校验参数问题
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public Result<String> handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+" "+fieldError.getDefaultMessage();
            }
        }
        return Result.validateFailed(message);
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        if (CharSequenceUtil.isNotBlank(ex.getMessage())) {
            return Result.failed(ResultCode.VALIDATE_FAILED, ex.getMessage());
        }
        return Result.failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (CharSequenceUtil.isNotBlank(msg)) {
            return Result.failed(ResultCode.VALIDATE_FAILED, msg);
        }
        return Result.failed(ResultCode.VALIDATE_FAILED);
    }

}
