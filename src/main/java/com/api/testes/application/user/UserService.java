package com.api.testes.application.user;

import java.util.List;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

public interface UserService{
    User saveUser(UserDTO user) throws Exception;

    List<User> findAll() throws Exception;

    User findById(Integer id) throws Exception;

    User update(User user) throws Exception;

    void delete(Integer id) throws Exception;
}
