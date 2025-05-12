package com.lkms.enums.errorCode.impl;

import com.lkms.enums.errorCode.ResponseCode;

public enum DataStatusCode implements ResponseCode {
    DATA_NOT_EXISTS(51, "数据不存在"),
    ALREADY_EXIST(52, "数据已存在"),
    INSERT_ERROR(53, "插入数据失败"),
    UPDATE_ERROR(54, "更新数据失败");

    DataStatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;
    private final String msg;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
