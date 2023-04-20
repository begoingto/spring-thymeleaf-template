package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.services.ArticleService;
import com.begoingto.thymeleafwebapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    String articlePage(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article";
    }

    @GetMapping("/{uuid}")
    String singleArticle(@PathVariable String uuid, Model model){
        Article article = articleService.singleArticle(uuid);
        model.addAttribute("article",article);
        return "single-article";
    }

    @GetMapping("/new")
    String newArticle(Article article,Model model){
        model.addAttribute("article",article);
        model.addAttribute("users",articleService.auths());
        model.addAttribute("categories",categoryService.getCategories());
        return "article-new";
    }

    @PostMapping(value = "/new")
    String doSaveArticle(@ModelAttribute @Valid Article article,
                         BindingResult result,
                         @RequestParam("author_id") Integer author_id,
                         @RequestParam("category_ids") List<Integer> category_ids,
                         @RequestParam("thumbnailFile") MultipartFile file,
                         Model model){

        // Filter Author by ID
        Author author =  articleService.auths().stream()
                .filter(a -> a.getId().equals(author_id))
                .findFirst()
                .orElse(null);

        // Filter Category in
        System.out.println(category_ids);
        List<Category> categories = categoryService.getCategories()
                        .stream().filter(category -> category_ids.contains(category.getId()))
                        .toList();
        //set author
        article.setAuthor(author);
        // set Categories
        article.setCategories(categories);

        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("article",article);
            return "article-new";
        }
        articleService.save(article,file);
        return "redirect:/article/new";
    }
}
