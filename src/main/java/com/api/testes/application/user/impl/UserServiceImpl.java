package com.api.testes.application.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.testes.application.user.UserService;
import com.api.testes.domain.user.User;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


}
