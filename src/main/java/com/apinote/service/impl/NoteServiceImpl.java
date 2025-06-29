package com.apinote.service.impl;

import com.apinote.model.Block;
import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;
import com.apinote.model.repository.BlockRepository;
import com.apinote.model.repository.NoteRepository;
import com.apinote.service.NoteService;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.NoteNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Note save(NoteDTO noteDTO) {

        Block block = blockRepository.findById(noteDTO.blockId())
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado"));

        Note note = new Note();
        note.setTitle(noteDTO.title());
        note.setDescription(noteDTO.description());
        note.setBlock(block);

        return noteRepository.save(note);
    }

    public Note update(Long id, NoteDTO noteDTO) {
        Note noteAtualizada = entityConversor.parseObject(noteRepository.findById(id), Note.class);
        noteAtualizada.setTitle(noteDTO.title());
        noteAtualizada.setDescription(noteDTO.description());
        noteAtualizada = noteRepository.saveAndFlush(noteAtualizada);
        return noteAtualizada;
    }

    public List<Note> list() {
        List<Note> notes = noteRepository.findAll();
        return notes;
    }

    public List<Note> listNotesByBlock(Long idBlock) {
        Block block = blockRepository.findById(idBlock)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado"));
        return noteRepository.findByBlock(idBlock);
    }

    public void delete(Long id) {
        Note note = noteRepository.findById(id)
           .orElseThrow(() -> new NoteNotFoundException("Nota não encontrada com o : " + id));
        noteRepository.deleteById(id);
    }

}
