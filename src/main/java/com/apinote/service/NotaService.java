package com.apinote.service;

import com.apinote.model.Bloco;
import com.apinote.model.Nota;
import com.apinote.model.dto.NotaDTO;
import com.apinote.model.repository.BlocoRepository;
import com.apinote.model.repository.NotaRepository;
import com.apinote.service.mapper.EntityConversor;
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
    private BlocoRepository blocoRepository;

    @Autowired
    EntityConversor entityConversor;

    public Nota criarNota(NotaDTO notaDTO) {
        Nota nota = entityConversor.parseObject(notaDTO, Nota.class);

        if (nota.getBloco() == null || nota.getBloco().getId() == null) {
            throw new IllegalArgumentException("O ID do bloco deve ser informado.");
        }

        Bloco bloco = blocoRepository.findById(nota.getBloco().getId())
                .orElseThrow(() -> new RuntimeException("Bloco não encontrado"));

        nota.setBloco(bloco);

        return notaRepository.save(nota);
    }

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

    public void deletarNota(Long id) {
        try {
            notaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar nota: " + e.getMessage());
        }
    }
}
