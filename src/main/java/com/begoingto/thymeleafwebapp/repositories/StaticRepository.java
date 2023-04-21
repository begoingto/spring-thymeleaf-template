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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Getter
public class StaticRepository {

    @Setter
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
        Author author = new Author(
                1, "Begoingto Me",
                "begoingto".toLowerCase(), "male",
                "atuny0@sohu.com",
                "1745 T Street Southeast",
                "https://robohash.org/hicveldicta.png",
                "/resources/img/default/article.png"
        );

        authors = new ArrayList<>(){{
            add(author);
            add(new Author(
                    2, "Sheldon Quigley",
                    "Sheldon".toLowerCase(), "male",
                    "hbingley1@plala.or.jp",
                    "6007 Applegate Lane",
                    "https://robohash.org/doloremquesintcorrupti.png",
                    "https://i.pinimg.com/originals/af/97/98/af979833dc3c10cec2d84d6f3b0a212f.png"
            ));

            add(new Author(
                    3, "Terrill Hills",
                    "Terrill".toLowerCase(), "male",
                    "rshawe2@51.la",
                    "6007 Applegate Lane",
                    "https://robohash.org/consequunturautconsequatur.png",
                    "/resources/img/default/article.png"
            ));

            add(new Author(
                    4, "Trycia Fadel",
                    "dpierrof".toLowerCase(), "female",
                    "dpierrof@vimeo.com",
                    "314 South 17th Street",
                    "https://robohash.org/porronumquamid.png",
                    "https://th.bing.com/th/id/R.e1c660810d5ebf2f6ff3fd7a77b6d0f0?rik=Y7SdLsUVGGzwSA&pid=ImgRaw&r=0"
            ));
        }};

        categories = new ArrayList<>(){{
            for (int i = 1; i < 11; i++){
                add(new Category(i,faker.pokemon().name(),faker.color().hex()));
            }
        }};
        Random random = new Random();
        articles = new ArrayList<>() {{

            for (int i = 0; i < 15; i++) {

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
