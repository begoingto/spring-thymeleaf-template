package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author saveAuthor(Author author, MultipartFile profile,MultipartFile cover);

    Author getAuthorById(Integer id);
}
