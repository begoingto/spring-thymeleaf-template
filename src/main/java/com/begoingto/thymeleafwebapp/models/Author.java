package com.begoingto.thymeleafwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Integer id;
    private String name;
    private String username;
    private String gender;
    private String email;
    private String address;
    private String avatar;
    private String cover;
}
