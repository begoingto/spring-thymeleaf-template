package com.begoingto.thymeleafwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/testing")
    String homePage(){
        return "index";
    }

    @GetMapping("/testing/article")
    String articlePage(){
        return "article";
    }
}
