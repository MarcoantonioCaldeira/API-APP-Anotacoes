package com.apinote.controller;

import com.apinote.model.User;
import com.apinote.model.dto.UserDTO;
import com.apinote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "rest/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create",
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        User userCreated = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário criado com sucesso.",
                        "dados", userCreated
                )
        );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }

    @GetMapping(value = "/list",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<User> listUser() {
        return userService.listUser();
    }
}
