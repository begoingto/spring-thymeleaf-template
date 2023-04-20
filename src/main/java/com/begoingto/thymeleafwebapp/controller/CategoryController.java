package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;
import com.begoingto.thymeleafwebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/{id}")
    String getCategoryById(@PathVariable String id, Model model){
        CategoryArticles catArt = categoryService.getCategoryById(Integer.parseInt(id));
        model.addAttribute("catArt",catArt);
        return "single-category";
    }
}
