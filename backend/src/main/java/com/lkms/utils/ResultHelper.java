package com.lkms.utils;

import com.lkms.enums.errorCode.ResponseCode;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.vo.ResultVo;
import org.springframework.stereotype.Component;

/**
 * @description 工具类, 将数据包装后返回给前端, 不直接 new ResultVO 返回
 */
@Component
public class ResultHelper {
    public <T> ResultVo<T> success(T data) {
        return new ResultVo<>(DefaultStatusCode.REQUEST_SUCCESS, data);
    }

    public <T> ResultVo<T> success() {
        return new ResultVo<>(DefaultStatusCode.REQUEST_SUCCESS);
    }

    public <T> ResultVo<T> fail(ResponseCode errorCode) {
        return new ResultVo<>(errorCode);
    }

    public <T> ResultVo<T> result(ResponseCode errorCode, T data) {
        return new ResultVo<>(errorCode, data);
    }
}
