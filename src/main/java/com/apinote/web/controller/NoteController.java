package com.apinote.web.controller;

import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;
import com.apinote.service.impl.NoteServiceImpl;
import com.apinote.web.SystemMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rest/note")
public class NoteController {

    @Autowired
    NoteServiceImpl noteServiceImpl;


    @RequestMapping(value = "/create")
    public ResponseEntity<Note> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteServiceImpl.save(noteDTO));
    }

    @RequestMapping(value = "/update/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Note notes = noteServiceImpl.update(id, noteDTO);
        SystemMessage<Note> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota criada com sucesso.", notes);
        return ResponseEntity.ok().body(userMessage);
    }

    @RequestMapping(value = "/list",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Note> listNote() {
        return noteServiceImpl.list();
    }

    @RequestMapping(value = "/list/{idBlock}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> listNoteByBlock(@PathVariable("idBlock") Long idBlock) {
        List<Note> notes = noteServiceImpl.listNotesByBlock(idBlock);
        SystemMessage<List<Note>> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota lida com sucesso.", notes);
        return ResponseEntity.ok().body(userMessage);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
        noteServiceImpl.delete(id);
        SystemMessage<Note> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota deletada com sucesso.", null);
        return ResponseEntity.ok().body(userMessage);
    }
}
