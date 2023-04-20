package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
}
