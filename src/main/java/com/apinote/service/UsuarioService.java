package com.apinote.service;

import com.apinote.model.Usuario;
import com.apinote.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
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
}
