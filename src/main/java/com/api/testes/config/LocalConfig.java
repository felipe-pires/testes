package com.api.testes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.testes.application.user.UserRepository;
import com.api.testes.domain.user.User;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User user1 = new User(null, "felipe", "felipe@teste.com", "123456");
        User user2 = new User(null, "teste", "teste@teste.com", "123456");
        repository.saveAll(List.of(user1, user2));
    }
}
