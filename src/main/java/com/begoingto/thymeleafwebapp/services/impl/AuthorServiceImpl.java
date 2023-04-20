package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.repositories.StaticRepository;
import com.begoingto.thymeleafwebapp.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final StaticRepository staticRepository;
    @Override
    public List<Author> getAuthors() {
        return staticRepository.getAuthors();
    }
}
