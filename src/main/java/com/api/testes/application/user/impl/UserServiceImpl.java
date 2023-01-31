package com.api.testes.application.user.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.testes.application.user.UserRepository;
import com.api.testes.application.user.UserService;
import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;
import com.api.testes.exceptions.BusinessException;
import com.api.testes.exceptions.DataIntegratyViolationException;
import com.api.testes.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User saveUser(UserDTO userDto) throws BusinessException {
        log.info("Salvando usuario: " + userDto.getName());

        existsEmail(userDto);
        User user = mapper.map(userDto, User.class);

        try {
            return repository.save(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar usuario");
        }
    }

    @Override
    public List<User> findAll() throws NotFoundException {
        log.info("Buscando todos os usuarios...");
        List<User> users = repository.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("Nenhum usuario encontrado");
        }

        return users;
    }

    @Override
    public User findById(Integer id) throws Exception {
        log.info("Buscando usuario com id: " + id);
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    @Override
    public User update(UserDTO userDto) throws Exception {
        log.info("Atualizando usuario...");

        existsEmail(userDto);

        try {
            User user = mapper.map(userDto, User.class);

            return repository.save(user);
        } catch (Exception e) {
            log.info("Erro ao atualizar usuario");
            throw new BusinessException("Erro ao atualizar usuario");
        }
    }

    private void existsEmail(UserDTO userDTO) {
        Optional<User> optional = repository.findByEmail(userDTO.getEmail());
        if (optional.isPresent() && !optional.get().getId().equals(userDTO.getId())) {
            throw new DataIntegratyViolationException("email ja existe na base de dados");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        log.info("Deletando usuario com id: " + id);
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) throws Exception {
        log.info("Buscando usuario com email: " + email);
        Optional<User> user = repository.findByEmail(email);
        return user.orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }
}
