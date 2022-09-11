package com.demo.carrental.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERROR(1001, "backend system error"),
    NORMAL_ERROR(1002, "operation failed"),
    INVALID_PARAMS(1003, "invalid param"),
    INVALID_TOKEN(1004, "invalid token"),
    TOKEN_EXPIRED(1005, "token expired");

    private int errorCode;
    private String errorMsg;

    ErrorCode(int code, String errorMsg){
        this.errorCode = code;
        this.errorMsg = errorMsg;
    }
}
