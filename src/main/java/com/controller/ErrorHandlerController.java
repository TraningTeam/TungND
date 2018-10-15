package com.controller;

import com.util.Constant;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    /**
     * Catch a Sql Grammar Exception
     *
     * @return exception view
     */
    @ExceptionHandler(SQLGrammarException.class)
    protected ModelAndView catchExceptionSQLGrammar() {
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject(Constant.ATTRIBUTE_STATUS_CODE, "500");
        modelAndView.addObject(Constant.ATTRIBUTE_MESSAGE, "Lỗi kết nối");
        return modelAndView;
    }

    /**
     * Catch a Parse Exception
     *
     * @return exception view
     */
    @ExceptionHandler(ParseException.class)
    protected ModelAndView catchExceptionParse() {
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject(Constant.ATTRIBUTE_STATUS_CODE, "500");
        modelAndView.addObject(Constant.ATTRIBUTE_MESSAGE, "Lỗi convert ngày tháng");
        return modelAndView;
    }

    /**
     * Catch a Runtime Exception
     *
     * @return list insurance view
     */
    @ExceptionHandler(RuntimeException.class)
    protected ModelAndView catchExceptionRuntime() {
        return new ModelAndView("redirect:" + Constant.URL_LIST_INSURANCE);
    }
}
