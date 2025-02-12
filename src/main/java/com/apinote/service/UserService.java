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
    public User criarUsuario(UserDTO usuario) {

        if(userRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyExistisExceptions("Email já cadastrado");
        }

        User userSalvo = entityConversor.parseObject(usuario, User.class);
        return userRepository.save(userSalvo);
    }

    public void deletarUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        userRepository.deleteById(id);
    }

    public List<User> listarUsuario() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public UserDTO buscarUsuarioPorId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        return entityConversor.parseObject(user, UserDTO.class);
    }
}
