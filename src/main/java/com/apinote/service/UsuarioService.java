package com.apinote.service;

import com.apinote.model.Usuario;
import com.apinote.model.dto.UsuarioDTO;
import com.apinote.model.repository.UsuarioRepository;
import com.apinote.service.exceptions.EmailAlreadyExistisExceptions;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityConversor entityConversor;


    @Transactional
    public Usuario criarUsuario(UsuarioDTO usuario) {

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyExistisExceptions("Email já cadastrado");
        }

        Usuario usuarioSalvo = entityConversor.parseObject(usuario, Usuario.class);
        return usuarioRepository.save(usuarioSalvo);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));
        return entityConversor.parseObject(usuario, UsuarioDTO.class);
    }
}
