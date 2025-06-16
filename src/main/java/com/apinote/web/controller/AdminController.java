package com.apinote.web.controller;
import com.apinote.model.User;
import com.apinote.service.impl.AdminServiceImpl;
import com.apinote.web.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "rest/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @GetMapping(value = "/list",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<User> listUsers() {
        return adminService.listUsers();
    }

    @GetMapping(value = "/set-to-admin/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> promoveToAdmin(@PathVariable("id") Long id) {
        User user = adminService.promoveToAdmin(id);
        SystemMessage<User> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Usuário alterado com sucesso.", user);
        return ResponseEntity.ok().body(userMessage);
    }

    @GetMapping(value = "/set-to-user/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> promoveToUser(@PathVariable("id") Long id) {
        User user = adminService.demoteToUser(id);
        SystemMessage<User> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Usuário alterado com sucesso.", user);
        return ResponseEntity.ok().body(userMessage);
    }
}
