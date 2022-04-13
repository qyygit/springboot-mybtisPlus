//package com.atguigu.boot.exception;
//
//
//import com.atguigu.boot.common.result.R;
//import com.atguigu.boot.common.result.ResultEnum;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})  //处理异常
//    public R  handleArithException(ArithmeticException e){
//
//        log.error("异常是：{}",e);
//
//        return R.setResult(ResultEnum.ERROR);
//    }
//}