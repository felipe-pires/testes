package com.api.testes.application.user.impl;

import com.api.testes.application.user.UserRepository;
import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;
import com.api.testes.exceptions.DataIntegratyViolationException;
import com.api.testes.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private static final int ID = 1;
    private static final String FELIPE = "felipe";
    private static final String FELIPE_EMAIL = "felipeTest@gmail.com";
    private static final String FELIPE_PASSWORD = "123456";

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
    void whenCreateThenReturnSucess() {
        when(repository.save(Mockito.any())).thenReturn(user);
        User response = service.saveUser(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertTrue(!response.getEmail().isEmpty());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
        try {
            userDTO.setId(2);
            service.saveUser(userDTO);
        } catch (Exception e) {
           assertEquals(DataIntegratyViolationException.class, e.getClass());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(FELIPE_EMAIL, response.get(0).getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() throws Exception {
        when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertTrue(!response.getName().isEmpty());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() throws Exception {
        when(repository.findById(Mockito.anyInt())).thenThrow(new NotFoundException("Usuario nao encontrado"));
        
        try {
            service.findById(ID);
        } catch (Exception e) {
           assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() throws Exception {
        when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertTrue(!response.getEmail().isEmpty());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
       
        try {
            service.update(userDTO);
        } catch (Exception e) {
           assertEquals(DataIntegratyViolationException.class, e.getClass());
        }
    }

    @Test
    void deleteWithSuccess() throws Exception {
      when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void deleteWithObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new NotFoundException("Usuario nao encontrado"));
        try {
            service.delete(ID);
        } catch (Exception e) {
           assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void whenFindByEmailReturnAnUserInstance() throws Exception {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        User response = service.findByEmail(FELIPE_EMAIL);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertTrue(!response.getEmail().isEmpty());

    }

    @Test
    void whenFindByEmailReturnAnNotFoundException(){
        when(repository.findByEmail(anyString())).thenThrow(new NotFoundException("Usuario nao encontrado"));
        try {
            service.findByEmail(FELIPE_EMAIL);
        } catch (Exception e) {
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    private void startUser() {
        user = new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        userDTO = new UserDTO(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD);
        optionalUser = Optional.of(new User(ID, FELIPE, FELIPE_EMAIL, FELIPE_PASSWORD));
    }
}