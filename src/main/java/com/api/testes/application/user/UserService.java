package com.api.testes.application.user;

import java.util.List;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

public interface UserService {
    User saveUser(UserDTO user) throws Exception;

    List<User> findAll() throws Exception;

    User findById(Integer id) throws Exception;

    User findByEmail(String email) throws Exception;

    User update(UserDTO user) throws Exception;

    void delete(Integer id) throws Exception;
}
