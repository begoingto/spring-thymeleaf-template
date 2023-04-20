package com.begoingto.thymeleafwebapp.controller;

import com.begoingto.thymeleafwebapp.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    String authors(Model model){
        model.addAttribute("authors",authorService.getAuthors());
        return "authors";
    }
}
