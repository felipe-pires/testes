package com.api.testes.application.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() throws Exception {

        List<UserDTO> usersDto = service.findAll().stream().map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(usersDto);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody User user) throws Exception {
        return new ResponseEntity(service.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody User user) throws Exception {
        return new ResponseEntity(service.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
