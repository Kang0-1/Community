package com.kang.handler;

import com.kang.domain.Result;
import com.kang.exception.CaptchaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kang
 * @description 全局异常处理器
 * @date 2023/3/16 18:21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description security异常捕获
     * @param e
     * @return Result.error
     */
    @ExceptionHandler(CaptchaException.class)
    public Result handler(CaptchaException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * @description 实体校验异常捕获
     * @param e
     * @return Result.error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().get(0);
        log.error("实体校验异常,-------------{}",objectError.getDefaultMessage());
        return Result.error(objectError.getDefaultMessage());
    }


    /**
     * @deprecated 运行时异常 处理
     * @param e
     * @return Result.error
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandle(RuntimeException e){
        log.error("运行时异常,--------------{}",e.getMessage());
        return Result.error(500,e.getMessage());
    }

    /**
     * @description 系统其他异常处理
     * @param e
     * @return Result.error
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        log.error("系统异常,--------------{}",e.getMessage());
        return Result.error(500,e.getMessage());
    }

    /**
     * @deprecated Assert异常
     * @param e
     * @return Result.error
     */
    @ExceptionHandler(IllegalAccessException.class)
    public Result handler(IllegalAccessException e){
        log.error("Assert异常,--------------{}",e.getMessage());
        return Result.error(e.getMessage());
    }

}
