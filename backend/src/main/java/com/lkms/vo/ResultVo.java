package com.lkms.vo;

import com.lkms.enums.errorCode.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    /*返回状态码*/
    private Integer code;
    /*携带数据*/
    private T data = null;
    /*返回信息*/
    private String msg;

    public ResultVo(Integer code) {
        this.code = code;
    }

    public ResultVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(ResponseCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ResultVo(ResponseCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

}