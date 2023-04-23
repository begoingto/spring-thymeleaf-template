package com.begoingto.thymeleafwebapp.services;

import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;
import com.github.javafaker.Cat;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    CategoryArticles getCategoryById(Integer id);
    Category save(Category category);
    boolean delete(Integer id);
    Category update(CategoryArticles categoryArticles);
}
