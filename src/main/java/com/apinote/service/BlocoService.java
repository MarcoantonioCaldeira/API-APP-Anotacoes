package com.apinote.service;

import com.apinote.model.Bloco;
import com.apinote.model.Nota;
import com.apinote.model.Usuario;
import com.apinote.model.repository.BlocoRepository;
import com.apinote.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BlocoService {

    @Autowired
    BlocoRepository blocoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Bloco criarBloco(Bloco bloco) {
        if (bloco.getUsuario() == null || bloco.getUsuario().getId() == null) {
            throw new IllegalArgumentException("O ID do bloco deve ser informado.");
        }

        Usuario usuario = usuarioRepository.findById(bloco.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Bloco não encontrado"));

        bloco.setUsuario(usuario);

        return blocoRepository.save(bloco);
    }

    @Transactional
    public Bloco atualizarBloco(Long id, Bloco entity) {
        Bloco blocoAtualizado = blocoRepository.findById(id).get();
        blocoAtualizado.setTitulo(entity.getTitulo());
        blocoAtualizado.setDescricao(entity.getDescricao());
        blocoAtualizado = blocoRepository.saveAndFlush(blocoAtualizado);
        return blocoAtualizado;
    }

    public List<Bloco> listarBloco() {
        List<Bloco> blocos = blocoRepository.findAll();
        return blocos;
    }

    @Transactional
    public void deletarBloco(Long id) {
        try {
            blocoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar bloco: " + e.getMessage());
        }
    }

    public Bloco buscarBlocoPorId(Long id) {
        return blocoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }
}
