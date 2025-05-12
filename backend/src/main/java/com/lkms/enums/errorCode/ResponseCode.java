package com.lkms.enums.errorCode;

import com.lkms.enums.BaseEnum;

public interface ResponseCode extends BaseEnum {
    /**
     * @return 得到错误码
     */
    Integer getCode();

    /**
     * @return 得到信息
     */
    String getMsg();
}
