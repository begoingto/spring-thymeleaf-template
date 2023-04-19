package com.begoingto.thymeleafwebapp.repositories;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Getter
public class StaticRepository {

    private List<Article> articles;
    private List<Author> authors;
    private Faker faker;

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    void init() {
        articles = new ArrayList<>() {{
            for (int i = 0; i < 20; i++) {
                add(new Article(UUID.randomUUID(), faker.book().title(), "/resources/img/default/article"+ (i%2==0?"2":"") +".png", faker.book().author()));
            }
        }};

        authors = new ArrayList<>(){{
            add(new Author(
                  1, "Terry Medhurst",
                    "Terry_Medhurst".toLowerCase(), "male",
                    "atuny0@sohu.com",
                    "1745 T Street Southeast",
                    "https://robohash.org/hicveldicta.png",
                    "/resources/img/default/article.png"
            ));
            add(new Author(
                    2, "Sheldon Quigley",
                    "Sheldon".toLowerCase(), "male",
                    "hbingley1@plala.or.jp",
                    "6007 Applegate Lane",
                    "https://robohash.org/doloremquesintcorrupti.png",
                    "/resources/img/default/article.png"
            ));

            add(new Author(
                    3, "Terrill Hills",
                    "Terrill".toLowerCase(), "male",
                    "rshawe2@51.la",
                    "6007 Applegate Lane",
                    "https://robohash.org/consequunturautconsequatur.png",
                    "/resources/img/default/article.png"
            ));
        }};
    }
}