package com.api.testes.application.user;

import java.util.List;

import com.api.testes.domain.user.User;

public interface UserService{
    User saveUser(User user);

    List<User> findAll();

    User findById(Long id);

    User update(User user);

    void delete(Long id);
}
