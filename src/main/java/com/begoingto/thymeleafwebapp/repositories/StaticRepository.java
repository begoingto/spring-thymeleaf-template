package com.begoingto.thymeleafwebapp.repositories;

import com.begoingto.thymeleafwebapp.models.Article;
import com.begoingto.thymeleafwebapp.models.Author;
import com.begoingto.thymeleafwebapp.models.Category;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Getter
public class StaticRepository {
    private List<Article> articles;
    private List<Author> authors;
    private List<Category> categories;
    private Faker faker;

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    void init() {
        Random random = new Random();
        Author author = new Author(
                1, "Begoingto Me",
                "begoingto".toLowerCase(), "male",
                "begoingto.me@gmail.com",
                faker.address().fullAddress()+", "+ faker.country().name(),
                "https://robohash.org/hicveldicta.png",
                "/resources/img/default/article.png"
        );

        List<String> gender = new ArrayList<>(List.of("male","Female"));
        List<String> avatars = new ArrayList<>(List.of(
                "https://robohash.org/doloremquesintcorrupti.png",
                "https://robohash.org/consequunturautconsequatur.png",
                "https://robohash.org/hicveldicta.png",
                "https://robohash.org/porronumquamid.png"
        ));

        authors = new ArrayList<>(){{
            add(author);
            for (int i=2;i<5;i++){
                add(new Author(
                        i,
                        faker.book().author(),
                        faker.name().username().toLowerCase(), gender.get(random.nextInt(2)),
                        faker.internet().safeEmailAddress(),
                        faker.address().fullAddress()+", "+ faker.country().name(),
                        avatars.get(random.nextInt(0,avatars.size())),
                        "https://th.bing.com/th/id/R.fd7ee12cef5592a445403b4ffb33c182?rik=TYNv1gkkSzqb7w&pid=ImgRaw&r=0"
                ));
            }
        }};

        categories = new ArrayList<>(){{
            for (int i = 1; i < 11; i++){
                add(new Category(i,faker.pokemon().name(),faker.color().hex()));
            }
        }};

        articles = new ArrayList<>() {{

            for (int i = 0; i < 12; i++) {

                add(
                        new Article(
                                UUID.randomUUID(),
                                faker.book().title(),
                                "/resources/img/default/article"+ (i%2==0?"2":"") +".png",
                                authors.get(random.nextInt(authors.size())),
                                faker.lorem().paragraphs(10).toString(),
                                categories.stream().filter(cat->cat.getId().equals(random.nextInt(categories.size())))
                                        .collect(Collectors.toList())
                        )
                );
            }
        }};

    }
}
