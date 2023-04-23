package com.begoingto.thymeleafwebapp.services.impl;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Category;
import com.begoingto.thymeleafwebapp.models.CategoryArticles;
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

    @Override
    public Category save(Category category) {
        category.setId(this.getCategories().size()+1);
        this.staticRepository.getCategories().add(category);
        System.out.println("Category has been saved.");
        return category;
    }

    @Override
    public boolean delete(Integer id) {
        CategoryArticles categoryArticles = this.getCategoryById(id);
        this.staticRepository.getCategories().remove(categoryArticles.category());
        for (Article article : categoryArticles.articles()){
            this.staticRepository.getArticles().remove(article);
        }
        System.out.println("Category has been deleted.");
        return true;
    }

    @Override
    public Category update(CategoryArticles categoryArticles) {
        Category oldCategory = this.getCategory(categoryArticles.category());
        // Update category
        int index = this.getIndex(categoryArticles.category());
        this.staticRepository.getCategories().set(index, categoryArticles.category());

        // update category in article
        if (!categoryArticles.articles().isEmpty()){
            staticRepository.getArticles().stream().filter(article -> article.getCategories().contains(oldCategory))
                    .forEach(article -> {
                        int i = article.getCategories().indexOf(oldCategory);
                        article.getCategories().set(i,categoryArticles.category());
                    });
        }


        return categoryArticles.category();
    }

    private int getIndex(Category category){
        Category category1 = getCategory(category);
        int index = this.staticRepository.getCategories().indexOf(category1);
        return index;
    }

    private Category getCategory(Category category) {
        Category category1 = this.staticRepository.getCategories().stream().filter(category2 -> category2.getId().equals(category.getId())).findFirst().orElse(null);
        return category1;
    }

    private List<Article> getArticlesByCategory(Category category) {
        List<Article> articles = staticRepository.getArticles().stream()
                .filter(article -> article.getCategories().contains(category))
                .toList();
        return articles;
    }
}
