package com.demo.carrental.common;

import lombok.Data;

@Data
public class Result {

    private int code;

    private String message;

    private Boolean success;

    private Object data;

    public static Result success(){
        return success("ok", null);
    }

    public static Result success(Object data){
        return success("ok", data);
    }

    public static Result success(String msg, Object data){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(1000);
        r.setData(data);
        r.setMessage(msg);
        return r;
    }

    public static Result fail(int code, String msg){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static Result fail(ErrorCode errorCode){
        return fail(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    public static Result fail(ErrorCode errorCode, String msg){
        return fail(errorCode.getErrorCode(), msg);
    }

    public static Result fail(String msg){
        return fail(ErrorCode.NORMAL_ERROR, msg);
    }
}
