package com.begoingto.thymeleafwebapp.uttils;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeans {
    @Bean
    public Faker faker(){
        return new Faker();
    }
}
