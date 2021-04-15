package com.rjgc.exceptions;

import lombok.Data;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:42
 */
@Data
public class ResBody<T> {
    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T result;

    /**
     * 成功
     *
     * @return resBody
     */
    public static <T> ResBody<T> success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data data
     * @return resBody
     */
    public static <T> ResBody<T> success(T data) {
        ResBody<T> rb = new ResBody<>();
        rb.setCode(ExceptionsEnum.SUCCESS.getResCode());
        rb.setMessage(ExceptionsEnum.SUCCESS.getResMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResBody<T> error(BizException errorInfo) {
        ResBody<T> rb = new ResBody<>();
        rb.setCode(errorInfo.getExp().getResCode());
        rb.setMessage(errorInfo.getExp().getResMsg());
        rb.setResult(null);
        return rb;
    }

    public static <T> ResBody<T> error(int errorCode, String errorInfo) {
        ResBody<T> rb = new ResBody<>();
        rb.setCode(errorCode);
        rb.setMessage(errorInfo);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return "ResBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
