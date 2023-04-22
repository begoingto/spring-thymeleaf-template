package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;
import com.begoingto.thymeleafwebapp.repositories.StaticRepository;
import com.begoingto.thymeleafwebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final StaticRepository staticRepository;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = staticRepository.getCategories().stream().map(category -> {
            category.setArticles(this.getArticlesByCategory(category));
            return category;
        }).toList();
        return categories;
    }

    @Override
    public CategoryArticles getCategoryById(Integer id) {
        Category category = staticRepository.getCategories().stream().filter(cat -> cat.getId().equals(id))
                            .findFirst().orElse(null);
        List<Article> articles = getArticlesByCategory(category);
        return new CategoryArticles(category,articles);
    }

    private List<Article> getArticlesByCategory(Category category) {
        List<Article> articles = staticRepository.getArticles().stream()
                .filter(article -> article.getCategories().contains(category))
                .toList();
        return articles;
    }
}
