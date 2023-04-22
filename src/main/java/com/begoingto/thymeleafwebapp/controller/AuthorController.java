package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.services.ArticleService;
import com.begoingto.thymeleafwebapp.services.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final ArticleService articleService;

    @GetMapping
    String authors(Model model){
        model.addAttribute("authors",authorService.getAuthors());
        return "authors/authors";
    }

    @GetMapping("/{id}")
    String authorProfile(@PathVariable Integer id,Model model){
        Author author = authorService.getAuthorById(id);
        List<Article> articles = articleService.getArticleByAuthor(author.getId());
        author.setArticles(articles);
        model.addAttribute("author",author);
        return "authors/author-profile";
    }

    @GetMapping("/new")
    String newAuthor(Author author, Model model){
        model.addAttribute("author",author);
        return "authors/author-new";
    }

    @PostMapping("/new")
    String saveAuthor(
            @ModelAttribute @Valid Author author,
            BindingResult result,
            @RequestParam("profile") MultipartFile profile,
            @RequestParam("coverFile") MultipartFile cover,
            Model model
    ){
        System.out.println(profile);
        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("author",author);
            return "authors/author-new";
        }
        authorService.saveAuthor(author,profile,cover);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    String editAuthor(@PathVariable Integer id,Model model){
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "authors/edit-profile";
    }

    @PostMapping("/update/{id}")
    String updateAuthor(@PathVariable Integer id,
                        @ModelAttribute @Valid Author author,
                        BindingResult result,
                        @RequestParam("profile") MultipartFile profile,
                        @RequestParam("coverFile") MultipartFile cover
    ){
        System.out.println(profile);
        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
        }
        authorService.updateAuthor(id,author,profile,cover);
        return "redirect:/authors";
    }


    @GetMapping("/delete/{id}")
    String deleteAuthor(@PathVariable Integer id){
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
