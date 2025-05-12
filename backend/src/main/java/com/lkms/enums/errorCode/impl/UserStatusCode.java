package com.lkms.enums.errorCode.impl;

import com.lkms.enums.errorCode.ResponseCode;

public enum UserStatusCode implements ResponseCode {
    EMAIL_HAS_BEEN_USED(301, "邮箱已经被占用"),
    EMAIL_NOT_EXIST(302, "邮箱不存在"),
    INCORRECT_USERNAME_OR_PASSWORD(303, "用户名或者密码不对"),
    TOKEN_INVALID(304, "无效的 token, 请重新登录"),
    INSUFFICIENT_PRIVILEGES(305, "权限不足");
    private final Integer code;
    private final String msg;

    UserStatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
