package com.project_lp2.project_lp2.controllers.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR = "error";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_STATUS = "error_status";
    public static final String ERROR_TIMESTAMP = "error_timestamp";
    public static final String ERROR_PATH = "error_path";
    public static final String ERROR_METHOD = "error_method";
    public static final String ERROR_EXCEPTION = "error_exception";
    public static final String ERROR_MESSAGE_NOT_FOUND = "Resource not found";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleNotFoundException(Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ModelAndView modelAndView = new ModelAndView("views/views_error/ErrorRedirect");
        modelAndView.addObject(ERROR_MESSAGE, e.getMessage());
        modelAndView.addObject(ERROR_STATUS, 500); // You can customize the status code
        modelAndView.addObject(ERROR_TIMESTAMP, System.currentTimeMillis());
        modelAndView.addObject(ERROR_PATH, request.getRequestURI());
        modelAndView.addObject(ERROR_METHOD, request.getMethod());
        modelAndView.addObject(ERROR_EXCEPTION, e);
        return modelAndView;
    }
}
