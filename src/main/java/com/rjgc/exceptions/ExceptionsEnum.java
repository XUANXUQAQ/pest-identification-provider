package com.rjgc.exceptions;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:39
 */
public enum ExceptionsEnum {

    SUCCESS(20000, "成功"),
    IN_USE(40000, "仍然被占用"),
    INVALID_ID(40001, "无效的id"),
    DATABASE_FAILED(40002, "数据库操作失败");


    private final int resCode;
    private final String resMsg;

    ExceptionsEnum(int resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public int getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }
}
