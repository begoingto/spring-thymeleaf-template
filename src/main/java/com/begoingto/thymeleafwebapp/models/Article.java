package com.begoingto.thymeleafwebapp.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private UUID uuid;
    @NotBlank(message = "The title field is required.")
    private String title;

    private String thumbnail;

    @NotBlank(message = "The author field is required.")
    private String author;
}
