package com.api.testes.application.user.impl;

import com.api.testes.application.user.UserRepository;
import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

class UserServiceImplTest {

    public static final int ID = 1;
    public static final String FELIPE = "felipe";
    public static final String FELIPE_EMAIL = "felipe@gmail.com";
    public static final String FELIPE_PASSWORD = "123456";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    UserServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void saveUser() {
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() throws Exception {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        User user = service.findById(ID);

        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        assertEquals(ID, user.getId());
        assertTrue(!user.getName().isEmpty());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
    }

    private void startUser(){
        user = new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        userDTO = new UserDTO(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        optionalUser = Optional.of(new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD));
    }
}