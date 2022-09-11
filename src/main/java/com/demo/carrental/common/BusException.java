package com.demo.carrental.common;

import lombok.Getter;

@Getter
public class BusException extends Exception {

    private ErrorCode errorCode;

    public BusException(ErrorCode errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public BusException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public BusException(String errorMsg){
        super(errorMsg);
    }
}
