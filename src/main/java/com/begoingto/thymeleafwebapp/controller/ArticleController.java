package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.services.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

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
        model.addAttribute("authors",articleService.authUsername());
        return "article-new";
    }

    @PostMapping(value = "/new")
    String doSaveArticle(@ModelAttribute @Valid Article article,
                         BindingResult result,
                         @RequestParam("thumbnailFile") MultipartFile file,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("article",article);
            return "article-new";
        }
        articleService.save(article,file);
        return "redirect:/article/new";
    }
}
