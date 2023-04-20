package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.repositories.StaticRepository;
import com.begoingto.thymeleafwebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final StaticRepository staticRepository;

    @Override
    public List<Category> getCategories() {
        return staticRepository.getCategories();
    }
}
