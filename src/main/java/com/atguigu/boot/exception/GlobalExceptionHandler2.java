package com.atguigu.boot.exception;


import com.atguigu.boot.common.result.R;
import com.atguigu.boot.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler2 {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public R handleBusinessException(BizException ex, HttpServletResponse response){
        log.error("BizException:",ex);
        return R.error().message(ex.getMessage());
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R globalException(HttpServletResponse response, HttpMessageNotReadableException e){
        log.error("参数不能为空！",e);
        return R.setResult(ResultEnum.PARAM_EX);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R error(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求类型:",e);
        return R.setResult(ResultEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R globalException(HttpServletResponse response, MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error("参数校验异常：",e);
        return R.error().message(message);
    }

    @ExceptionHandler(BindException.class)
    public R error(BindException e) {
        log.error("BindException：",e);
        return R.error().message(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R error(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.error("MissingServletRequestParameterException：",ex);
        StringBuilder msg = new StringBuilder();
        msg.append("缺少必须的[").append(ex.getParameterType()).append("]类型的参数[").append(ex.getParameterName()).append("]");
        return R.error().message(msg.toString());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.error("MethodArgumentTypeMismatchException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("参数：[").append(eee.getName())
                .append("]的传入值：[").append(eee.getValue())
                .append("]与预期的字段类型：[").append(eee.getRequiredType().getName()).append("]不匹配");

        return R.error().message(msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public R illegalStateException(IllegalStateException ex, HttpServletRequest request) {
        log.error("IllegalStateException:", ex);
        return R.setResult(ResultEnum.ILLEGALA_ARGUMENT_EX);
    }

    @ExceptionHandler(NullPointerException.class)
    public R nullPointerException(NullPointerException ex, HttpServletRequest request) {
        log.error("NullPointerException:", ex);

        return R.setResult(ResultEnum.NULL_POINT_EX);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.error("IllegalArgumentException:", ex);
        return R.setResult(ResultEnum.ILLEGALA_ARGUMENT_EX);
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("请求类型(Content-Type)[").append(contentType.toString()).append("] 与实际接口的请求类型不匹配");
            return R.setResult(ResultEnum.MEDIA_TYPE_EX).message(msg.toString());
        }
        return R.setResult(ResultEnum.MEDIA_TYPE_EX).message("无效的Content-Type类型");
    }

    @ExceptionHandler(Exception.class)
    public R globalException(HttpServletResponse response, Exception e){
        log.error("系统异常",e);
        return R.error().message("服务器异常，请稍后再试！");
    }
    @ExceptionHandler(ArithmeticException.class)
    public String arithmeticException(HttpServletResponse response, Model model, Exception e){
        log.error("系统异常",e);
        model.addAttribute("msg","服务器异常，请重新登录");
        return "login"; //视图地址
    }



}
