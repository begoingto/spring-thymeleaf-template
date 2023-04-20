package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getAuthorById(Integer id);
}
