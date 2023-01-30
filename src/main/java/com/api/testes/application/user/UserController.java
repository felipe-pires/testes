package com.api.testes.application.user;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.testes.domain.dto.UserDTO;
import com.api.testes.domain.user.User;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String ID = "/{id}";

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
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDto) throws Exception {
        User user = service.saveUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping("/email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.ok().body(mapper.map(service.findByEmail(email), UserDTO.class));
    }

    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO user) throws Exception {
        user.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(user), UserDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
