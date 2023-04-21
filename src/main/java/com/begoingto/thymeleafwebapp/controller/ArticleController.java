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
        return "articles/article";
    }

    @GetMapping("/{uuid}")
    String singleArticle(@PathVariable String uuid, Model model){
        Article article = articleService.singleArticle(uuid);
        model.addAttribute("article",article);
        return "/articles/single-article";
    }

    @GetMapping("/new")
    String newArticle(Article article,Model model){
        model.addAttribute("article",article);
        model.addAttribute("users",articleService.auths());
        model.addAttribute("categories",categoryService.getCategories());
        return "articles/article-new";
    }

    @PostMapping(value = "/new")
    String doSaveArticle(@ModelAttribute @Valid Article article,
                         BindingResult result,
                         @RequestParam("author_id") Integer author_id,
                         @RequestParam("category_ids") List<Integer> category_ids,
                         @RequestParam("thumbnailFile") MultipartFile file,
                         Model model){

        Article article1 = setRequestArticle(article, author_id, category_ids);

        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("article",article);
            return "articles/article-new";
        }
        articleService.save(article1,file);
        return "redirect:/article";
    }

    private Article setRequestArticle(Article article, Integer author_id, List<Integer> category_ids) {
        // Filter Author by ID
        Author author =  articleService.auths().stream()
                .filter(a -> a.getId().equals(author_id))
                .findFirst()
                .orElse(null);

        // Filter Category in
        List<Category> categories = categoryService.getCategories()
                        .stream().filter(category -> category_ids.contains(category.getId()))
                        .toList();
        //set author
        article.setAuthor(author);
        // set Categories
        article.setCategories(categories);
        return article;
    }

    @GetMapping("/delete/{uuid}")
    String deleteArticle(@PathVariable String uuid){
        articleService.deleteArticle(uuid);
        return "redirect:/article";
    }

    @GetMapping("/edit/{uuid}")
    String editArticle(@PathVariable String uuid,Model model){
        Article article = articleService.singleArticle(uuid);
        model.addAttribute("article",article);
        model.addAttribute("users",articleService.auths());
        model.addAttribute("categories",categoryService.getCategories());
        return "articles/article-edit";
    }

    @PostMapping("/update/{uuid}")
    String updateArticle(@PathVariable String uuid,
                         @ModelAttribute @Valid Article article,
                         BindingResult result,
                         @RequestParam("author_id") Integer author_id,
                         @RequestParam("category_ids") List<Integer> category_ids,
                         @RequestParam("thumbnailFile") MultipartFile file){
        Article reqArticle = setRequestArticle(article, author_id, category_ids);
        Article a = articleService.updateArticle(uuid, reqArticle,file);
        return "redirect:/article";
    }
}
