package com.apinote.service;

import com.apinote.model.Nota;
import com.apinote.model.Usuario;
import com.apinote.model.repository.NotaRepository;
import com.apinote.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotaService {

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Nota criarNota(Nota nota) {
        // Verificar se o usuário existe
        if (nota.getUsuario() == null || nota.getUsuario().getId() == null) {
            throw new IllegalArgumentException("O ID do usuário deve ser informado.");
        }

        Usuario usuario = usuarioRepository.findById(nota.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Associar o usuário à nota
        nota.setUsuario(usuario);

        // Salvar a nota
        return notaRepository.save(nota);
    }

    @Transactional
    public Nota atualizarNota(Long id, Nota entity) {
        Nota notaAtualizada = notaRepository.findById(id).get();
        notaAtualizada.setTitulo(entity.getTitulo());
        notaAtualizada.setDescricao(entity.getDescricao());
        notaAtualizada = notaRepository.saveAndFlush(notaAtualizada);
        return notaAtualizada;
    }

    public List<Nota> listarNotas() {
        List<Nota> notas = notaRepository.findAll();
        return notas;
    }

    @Transactional
    public void deletarNota(Long id) {
        try {
            notaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar nota: " + e.getMessage());
        }
    }
}
