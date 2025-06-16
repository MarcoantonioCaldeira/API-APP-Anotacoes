package com.apinote.service.impl;

import com.apinote.model.User;
import com.apinote.model.dto.UserDTO;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.UserService;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityConversor entityConversor;


    @Transactional
    public User save(UserDTO userDTO) {
        if (userRepository.findByLogin(userDTO.login()) != null) {
            throw new UserNotFoundException("Login já cadastrado");
        }

        if (!userDTO.password().equals(userDTO.confirmPassword())) {
            throw new IllegalArgumentException("Senhas não conferem");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        var user = new User(userDTO.name(), userDTO.login(), userDTO.email(), encryptedPassword, userDTO.role(), userDTO.confirmPassword(), new HashSet<>());

        return userRepository.save(user);
    }


    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        userRepository.deleteById(id);
    }

    public User listById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        return user;
    }

    public User update(Long id, UserDTO entity) {
        User userUpdated = entityConversor.parseObject(userRepository.findById(id), User.class);
        userUpdated.setName(entity.name());
        userUpdated.setEmail(entity.email());
        userUpdated.setLogin(entity.login());
        userUpdated.setPassword(entity.password());
        userUpdated.setRole(entity.role());
        userUpdated = userRepository.saveAndFlush(userUpdated);
        return userUpdated;
    }

}
