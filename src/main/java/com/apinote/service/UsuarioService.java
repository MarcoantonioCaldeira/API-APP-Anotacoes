package com.apinote.service;

import com.apinote.model.Usuario;
import com.apinote.model.dto.UsuarioDTO;
import com.apinote.model.repository.UsuarioRepository;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityConversor entityConversor;

    public Usuario criarUsuario(UsuarioDTO usuario) {
        Usuario usuarioSalvo = entityConversor.parseObject(usuario, Usuario.class);

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioSalvo.getEmail());

        if(usuarioOptional.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        return usuarioRepository.save(usuarioSalvo);
    }

    public void deletarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    @Transactional
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return entityConversor.parseObject(usuario, UsuarioDTO.class);
    }
}
