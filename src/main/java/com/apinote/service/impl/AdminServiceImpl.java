package com.apinote.service.impl;

import com.apinote.model.User;
import com.apinote.model.UserRole;
import com.apinote.model.dto.UserDTO;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.AdminService;
import com.apinote.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User promoveToAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        user.setRole(UserRole.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User demoteToUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }
}
