package com.xshiwu.common.exception;

import com.xshiwu.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运行时异常
 * @author xsw
 * @version 1.0
 * @date 2021/4/11 21:03
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    //状态码
    private Integer code;

    //错误消息
    private String message;

    /**
     * @param message 错误消息
     */
    public BusinessException(String message) {
        this.message = message;
    }

    /**
     * @param message 错误消息
     * @param code    错误码
     */
    public BusinessException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * @param message 错误消息
     * @param code    错误码
     * @param cause   原始异常对象
     */
    public BusinessException(String message, Integer code, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    /**
     * @param resultCodeEnum 接收枚举类型
     */
    public BusinessException(ResponseEnum resultCodeEnum) {
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     * @param resultCodeEnum 接收枚举类型
     * @param cause          原始异常对象
     */
    public BusinessException(ResponseEnum resultCodeEnum, Throwable cause) {
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }
}

