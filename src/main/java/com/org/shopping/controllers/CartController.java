package com.org.shopping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cart");
        return mv;
    }
    @GetMapping("/checkout")
    public ModelAndView checkout(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("checkout");
        return mv;
    }
}
