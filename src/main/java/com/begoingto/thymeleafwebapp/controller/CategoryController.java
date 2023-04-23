package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;
import com.begoingto.thymeleafwebapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    String listCategory(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "category/categories";
    }

    @GetMapping("/new")
    String newCategory(Model model){
        model.addAttribute("category",new Category());
        return "category/category-new";
    }


    @GetMapping("/{id}")
    String getCategoryById(@PathVariable String id, Model model){
        CategoryArticles catArt = categoryService.getCategoryById(Integer.parseInt(id));
        model.addAttribute("catArt",catArt);
        return "category/single-category";
    }

    @PostMapping("/new")
    String saveCategory(@ModelAttribute @Valid Category category, BindingResult result,Model model){
        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("category", category);
            return  "category/category-new";
        }
        System.out.println(category);
        this.categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    String editCategory(@PathVariable Integer id,Model model){
        CategoryArticles catArt = categoryService.getCategoryById(id);
        model.addAttribute("category",catArt.category());
        return "category/category-edit";
    }

    @PostMapping("/update/{id}")
    String updateCategory(@PathVariable Integer id,
                          @ModelAttribute @Valid Category category,
                          BindingResult result,
                          Model model
                          ){
        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("category", category);
            return  "category/category-edit";
        }

        CategoryArticles categoryArticles = this.categoryService.getCategoryById(id);

        categoryArticles.category().setName(category.getName());
        categoryArticles.category().setColor(category.getColor());

        categoryService.update(categoryArticles);

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    String deleteCategory(@PathVariable Integer id, Model model){
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
