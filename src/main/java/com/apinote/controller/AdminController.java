package com.apinote.controller;

import com.apinote.model.User;
import com.apinote.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "rest/admin")
public class AdminController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value = "/list",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<User> listUser() {
        return userServiceImpl.listAll();
    }
}
