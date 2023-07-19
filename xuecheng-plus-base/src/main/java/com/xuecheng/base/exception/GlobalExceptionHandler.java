package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @description 全局异常处理器
 * @date 2023/2/27 14:06
 */
@Slf4j
@ControllerAdvice//控制器增强
public class GlobalExceptionHandler {

    //处理XueChengPlusException异常  此类异常是程序员主动抛出的可预知异常
    @ResponseBody//以json的格式返回数据
    @ExceptionHandler(XueChengPlusException.class)//表明此方法捕获XueChengPlusException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回指定状态码500
    public RestErrorResponse customException(XueChengPlusException e) {
        log.error("【系统异常】{}",e.getErrMessage(),e);
        return new RestErrorResponse(e.getErrMessage());
    }

    //捕获不可预知异常 Exception
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e) {

        log.error("【系统异常】{}",e.getMessage(),e);
        if(e.getMessage().equals("不允许访问")){
            return new RestErrorResponse("你没有权限操作此功能");
        }

        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doValidException(MethodArgumentNotValidException argumentNotValidException) {

        BindingResult bindingResult = argumentNotValidException.getBindingResult();
        //收集错误
        StringBuffer errMsg = new StringBuffer();
        //检验的错误信息
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            errMsg.append(fieldError.getDefaultMessage()).append(",");
        }
/*        fieldErrors.forEach(error -> {
            errMsg.append(error.getDefaultMessage()).append(",");
        });*/

        log.error(errMsg.toString());
        return new RestErrorResponse(errMsg.toString());
    }


}