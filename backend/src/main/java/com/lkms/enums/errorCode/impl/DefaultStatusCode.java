package com.lkms.enums.errorCode.impl;

import com.lkms.enums.errorCode.ResponseCode;

public enum DefaultStatusCode implements ResponseCode {

    REQUEST_SUCCESS(0, "一切 OK"),
    REQUEST_FAIL(1, "服务器有问题"),
    DATA_FAIL(2, "不存在这样的数据"),
    ES_FAIL(3, "es搜索出错");

    DefaultStatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;
    private final String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
