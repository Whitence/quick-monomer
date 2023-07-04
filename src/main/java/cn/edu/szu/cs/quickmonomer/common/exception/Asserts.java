package cn.edu.szu.cs.quickmonomer.common.exception;

import cn.edu.szu.cs.quickmonomer.common.api.IResult;

/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/19 21:54
 * @version 1.0
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IResult errorCode) {
        throw new ApiException(errorCode);
    }
}
