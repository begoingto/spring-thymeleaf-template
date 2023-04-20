package com.begoingto.thymeleafwebapp.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private UUID uuid;
    @NotBlank(message = "The title field is required.")
    private String title;

    private String thumbnail;

    //@NotNull(message = "The author field is required.")
    private Author author;

    private String description;

    private List<Category> categories;

}
