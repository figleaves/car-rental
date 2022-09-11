package com.demo.carrental.controller;

import com.demo.carrental.common.BusException;
import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;


@RestControllerAdvice
@Slf4j
public class BaseController {

    private static final Logger logger =
            LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    @ResponseBody
    public Result handleExp(Exception e){
        logger.error("exception:", e);
        if (e instanceof BusException) {
            BusException be = (BusException) e;
            return Result.fail(be.getErrorCode());
        } else if (e instanceof DateTimeParseException){
            return Result.fail(ErrorCode.INVALID_PARAMS, "failed to parse datetime ");
        } else {
            return Result.fail(ErrorCode.ERROR, e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleArgumentEx(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        log.info(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        return Result.fail(ErrorCode.INVALID_PARAMS, fieldError.getDefaultMessage());
    }
}
