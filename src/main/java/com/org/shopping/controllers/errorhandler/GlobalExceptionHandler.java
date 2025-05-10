package com.org.shopping.controllers.errorhandler;

import com.org.shopping.exceptions.ProductNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceAccessException.class)
    public ModelAndView handleResourceAccessException(ResourceAccessException ex){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/404");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleProductNotFoundException(ProductNotFoundException ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("errors/productNotFoundException");
        return mv;
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView handleFileNotFoundException(FileNotFoundException ex){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/fileNotFoundException");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(SecurityException.class)
    public ModelAndView handleSecurityException(SecurityException ex){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/securityException");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
}
