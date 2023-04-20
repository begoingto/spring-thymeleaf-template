package com.begoingto.thymeleafwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/{id}")
    String getCategory(@PathVariable Integer id, Model model){
        return "";
    }
}
