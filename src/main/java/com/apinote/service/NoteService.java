package com.apinote.service;

import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;

import java.util.List;

public interface NoteService{
    public Note save(NoteDTO noteDTO);
    public Note update(Long id, NoteDTO noteDTO);
    public List<Note> list();
    public List<Note> listNotesByBlock(Long idBlock);
    public void delete(Long id);
}
