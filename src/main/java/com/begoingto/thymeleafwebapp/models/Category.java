package com.begoingto.thymeleafwebapp.models;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Category {
    @NonNull
    private Integer id;
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @NotBlank
    private String color;
    private List<Article> articles;
}
