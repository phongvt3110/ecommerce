package com.org.shopping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @GetMapping("blog-list")
    public ModelAndView blog(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog");
        return mv;
    }

    @GetMapping("blog-single")
    public ModelAndView blogSingle(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog-single");
        return mv;
    }
}
