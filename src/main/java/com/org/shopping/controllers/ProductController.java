package com.org.shopping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shop");
        return mv;
    }
    @GetMapping("/product-details")
    public ModelAndView productDetails(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-details");
        return mv;
    }
}
