package com.apinote.service;

import com.apinote.model.Usuario;
import com.apinote.model.repository.UsuarioRepository;
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

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioOptional.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }
}
