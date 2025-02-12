package com.apinote.service;

import com.apinote.model.Bloco;
import com.apinote.model.Nota;
import com.apinote.model.Usuario;
import com.apinote.model.dto.BlocoDTO;
import com.apinote.model.dto.NotaDTO;
import com.apinote.model.repository.BlocoRepository;
import com.apinote.model.repository.NotaRepository;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.NoteNotFoundException;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    private BlocoRepository blocoRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Nota criarNota(NotaDTO notaDTO) {

        Bloco bloco = blocoRepository.findById(notaDTO.getBlocoId())
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado"));

        Nota nota = new Nota();
        nota.setTitulo(notaDTO.getTitulo());
        nota.setDescricao(notaDTO.getDescricao());
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
        Nota nota = notaRepository.findById(id)
           .orElseThrow(() -> new NoteNotFoundException("Nota não encontrada com o : " + id));
        notaRepository.deleteById(id);
    }
}
