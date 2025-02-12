package com.apinote.service;

import com.apinote.model.Block;
import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;
import com.apinote.model.repository.BlockRepository;
import com.apinote.model.repository.NoteRepository;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.NoteNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Note criarNota(NoteDTO noteDTO) {

        Block block = blockRepository.findById(noteDTO.getBlocoId())
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado"));

        Note note = new Note();
        note.setTitulo(noteDTO.getTitulo());
        note.setDescricao(noteDTO.getDescricao());
        note.setBloco(block);

        return noteRepository.save(note);
    }

    public Note atualizarNota(Long id, Note entity) {
        Note noteAtualizada = noteRepository.findById(id).get();
        noteAtualizada.setTitulo(entity.getTitulo());
        noteAtualizada.setDescricao(entity.getDescricao());
        noteAtualizada = noteRepository.saveAndFlush(noteAtualizada);
        return noteAtualizada;
    }

    public List<Note> listarNotas() {
        List<Note> notes = noteRepository.findAll();
        return notes;
    }

    public void deletarNota(Long id) {
        Note note = noteRepository.findById(id)
           .orElseThrow(() -> new NoteNotFoundException("Nota não encontrada com o : " + id));
        noteRepository.deleteById(id);
    }
}
