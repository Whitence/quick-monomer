package cn.edu.szu.cs.quickmonomer.common.api;
/**
 * @description: 结果码
 * @author whitence
 * @date 2023/4/19 21:42
 * @version 1.0
 */
public enum ResultCode implements IResult {
    // success
    SUCCESS(2000, "请求成功"),
    // failed
    FAILED(2005, "请求失败"),
    // validate_failed
    VALIDATE_FAILED(2004, "参数检验失败"),
    // unauthorized
    UNAUTHORIZED(2001, "暂未登录或token已经过期"),
    // forbidden
    FORBIDDEN(2003, "没有相关权限");
    /**
     * code
     */
    private final Integer code;
    /**
     * message
     */
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}