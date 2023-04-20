package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    CategoryArticles getCategoryById(Integer id);
}
