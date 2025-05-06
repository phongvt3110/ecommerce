package com.org.shopping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @GetMapping("contact-us")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contact-us");
        return mv;
    }
}
