package com.demo.carrental.common;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private Boolean success;

    private T data;

    public static <T> Result<T> success(){
        return success(ErrorCode.SUCCESS.getErrorMsg(), null);
    }

    public static <T> Result<T> success(T data){
        return success(ErrorCode.SUCCESS.getErrorMsg(), data);
    }

    public static <T> Result<T> success(String msg, T data){
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(ErrorCode.SUCCESS.getErrorCode());
        r.setData(data);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> fail(int code, String msg){
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> fail(ErrorCode errorCode){
        return fail(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String msg){
        return fail(errorCode.getErrorCode(), msg);
    }

    public static <T> Result<T> fail(String msg){
        return fail(ErrorCode.NORMAL_ERROR, msg);
    }
}
