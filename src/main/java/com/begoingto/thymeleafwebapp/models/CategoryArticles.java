package com.begoingto.thymeleafwebapp.models;

import java.util.List;

public record CategoryArticles(Category category, List<Article> articles) {
}
