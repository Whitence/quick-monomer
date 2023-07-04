package cn.edu.szu.cs.quickmonomer.common.exception;

import cn.edu.szu.cs.quickmonomer.common.api.IResult;

/**
 * @description: 接口异常类
 * @author hjc
 * @date 2023/4/19 21:51
 * @version 1.0
 */
public class ApiException extends RuntimeException {

    private IResult resultCode;

    public ApiException(IResult resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
    public ApiException(IResult errorCode,String msg) {
        super(msg);
        this.resultCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IResult getErrorCode() {
        return resultCode;
    }
}
