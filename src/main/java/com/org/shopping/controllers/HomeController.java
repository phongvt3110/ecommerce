package com.org.shopping.controllers;

import com.org.shopping.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/payment")
    public ModelAndView payment(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("payment");
        return mv;
    }

    @GetMapping("not-found")
    public ModelAndView notFound(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errors/404");
        return mv;
    }

    @GetMapping("test-exception-handler")
    public ModelAndView testExceptionHandler(){
        throw new IllegalArgumentException("Invalid argument");
    }

    @GetMapping("test-controller-advice")
    public ModelAndView testControllerAdvice(){
        throw new ResourceAccessException("Resource access exception");
    }

    @GetMapping("test-product-not-found")
    public ModelAndView testProductNotFoundException(){
        throw new ProductNotFoundException("Không tìm thấy sản phẩm");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("Caught in controller:" + ex.getMessage());
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test");
        return mv;
//        throw new ProductNotFoundException("Product not found");
    }
}
