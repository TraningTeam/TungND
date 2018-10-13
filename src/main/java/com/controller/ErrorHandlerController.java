package com.controller;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLGrammarException.class)
    protected ModelAndView catchExceptionSQLGrammar() {
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("status_code", "500");
        modelAndView.addObject("message", "Lỗi kết nối");
        return modelAndView;
    }

    @ExceptionHandler(ParseException.class)
    protected ModelAndView catchExceptionParse() {
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("status_code", "500");
        modelAndView.addObject("message", "Lỗi convert ngày tháng");
        return modelAndView;
    }

    @ExceptionHandler(RuntimeException.class)
    protected ModelAndView catchExceptionRuntime() {
        return new ModelAndView("redirect:/listInsurance");
    }
}
