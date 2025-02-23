package com.apinote.service;

import com.apinote.model.User;
import com.apinote.model.dto.UserDTO;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.exceptions.EmailAlreadyExistisExceptions;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityConversor entityConversor;


    @Transactional
    public User createUser(UserDTO userDTO) {

        if(userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new EmailAlreadyExistisExceptions("Email já cadastrado");
        }

        var user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setConfirmPassword(userDTO.confirmPassword());

        if(!userDTO.password().equals(userDTO.confirmPassword())) {
            throw new IllegalArgumentException("Senhas não conferem");
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        userRepository.deleteById(id);
    }

    public List<User> listUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public UserDTO searchUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        return entityConversor.parseObject(user, UserDTO.class);
    }
}
