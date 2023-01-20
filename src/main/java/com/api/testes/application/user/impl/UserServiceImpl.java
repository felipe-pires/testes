package com.api.testes.application.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.testes.application.user.UserRepository;
import com.api.testes.application.user.UserService;
import com.api.testes.domain.user.User;
import com.api.testes.exceptions.NotFoundException;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User user) throws Exception{
        return repository.save(user);
    }

    @Override
    public List<User> findAll() throws Exception{
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) throws Exception {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    @Override
    public User update(User user) throws Exception {
        findById(user.getId());
        return repository.save(user);
    }

    @Override
    public void delete(Integer id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
