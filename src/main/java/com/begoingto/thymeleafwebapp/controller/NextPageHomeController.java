package com.begoingto.thymeleafwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NextPageHomeController {
    @GetMapping("/nextpage")
    String homePage(){
        return "indexnextpage";
    }
}
