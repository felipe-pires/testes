package com.api.testes.application.user;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    void whenFindAllThenReturnListOfUsers() throws Exception {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<List<UserDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

    }

    @Test
    void whenSaveUserThenReturnSucess() throws Exception {
        when(service.saveUser(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.save(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = controller.findById(ID);

        assertNotNull(response.getBody());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertTrue(!response.getBody().getEmail().isEmpty());
    }

    @Test
    void whenFindByEmailThenReturnSuccess() throws Exception {
        when(service.findByEmail(anyString())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = controller.findByEmail(FELIPE_EMAIL);

        assertNotNull(response.getBody());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void whenUpdateThenReturnSucess() throws Exception {
        when(service.update(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void deleteWithSuccess() throws Exception {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<UserDTO> response = controller.delete(ID);

        assertNotNull(response);
        verify(service, times(1)).delete(ID);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startUser() {
        user = new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        userDTO = new UserDTO(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
    }
}