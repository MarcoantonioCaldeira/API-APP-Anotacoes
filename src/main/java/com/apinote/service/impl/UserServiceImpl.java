package com.apinote.service.impl;

import com.apinote.model.User;
import com.apinote.model.UserRole;
import com.apinote.model.dto.UserDTO;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.UserService;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import com.apinote.web.SystemMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityConversor entityConversor;


    @Override
    @Transactional
    public User save(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new UserNotFoundException("Login já cadastrado");
        }

        if (!userDTO.password().equals(userDTO.confirmPassword())) {
            throw new IllegalArgumentException("Senhas não conferem");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        var user = new User(
                userDTO.name(),
                userDTO.email(),
                encryptedPassword,
                userDTO.role(),
                new HashSet<>()
        );

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        userRepository.deleteById(id);
    }
    @Override
    public User listById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        return user;
    }
    @Override
    public User update(Long id, UserDTO entity) {
        User userUpdated = entityConversor.parseObject(userRepository.findById(id), User.class);
        userUpdated.setName(entity.name());
        userUpdated.setEmail(entity.email());
        userUpdated.setPassword(entity.password());
        userUpdated.setRole(entity.role());
        userUpdated = userRepository.saveAndFlush(userUpdated);
        return userUpdated;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

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
