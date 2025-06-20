package com.apinote.web.controller;

import com.apinote.model.Note;
import com.apinote.model.User;
import com.apinote.model.dto.NoteDTO;
import com.apinote.model.dto.UserDTO;
import com.apinote.service.impl.UserServiceImpl;
import com.apinote.web.SystemMessage;
import jakarta.validation.Valid;
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

import java.util.Map;

@RestController
@RequestMapping( value = "rest/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/create",
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO userDTO) {
        User userCreated = userServiceImpl.save(userDTO);
        SystemMessage<User> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Usuário criado com sucesso.", userCreated);
        return ResponseEntity.ok().body(userMessage);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        SystemMessage<User> userMessage = new SystemMessage<User>(HttpStatus.OK.value(), "Registro de id: " + id + "deletado com sucesso", null);
        return ResponseEntity.ok().body(userMessage);
    }

    @GetMapping(value = "/list/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> listByUser(@PathVariable("id") Long id) {
        User user = userServiceImpl.listById(id);
        SystemMessage<User> userMessage = new SystemMessage<User>(HttpStatus.OK.value(), "Usuario lido com sucesso", user);
        return ResponseEntity.ok().body(userMessage);
    }

    @RequestMapping(value = "/update/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userServiceImpl.update(id, userDTO);
        SystemMessage<User> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota criada com sucesso.", user);
        return ResponseEntity.ok().body(userMessage);
    }

}
