package com.lkms.exception;

import com.lkms.enums.errorCode.ResponseCode;

public class ServiceException extends RuntimeException {
    ResponseCode responseCode;

    public ServiceException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMsg();
    }
}
