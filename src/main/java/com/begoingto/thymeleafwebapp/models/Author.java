package com.begoingto.thymeleafwebapp.models;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Author {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String username;
    @NonNull
    private String gender;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @NonNull
    private String avatar;
    @NonNull
    private String cover;
    private List<Article> articles;
}
