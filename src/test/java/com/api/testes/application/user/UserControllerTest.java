package com.api.testes.application.user;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

    private static final int ID = 1;
    private static final String FELIPE = "felipe";
    private static final String FELIPE_EMAIL = "felipeTest@gmail.com";
    private static final String FELIPE_PASSWORD = "123456";

    @InjectMocks
    private UserController controller;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserService service;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        userDTO = new UserDTO(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
    }
}