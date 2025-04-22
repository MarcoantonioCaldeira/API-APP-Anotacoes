package com.apinote.controller;

import com.apinote.model.Block;
import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;
import com.apinote.service.impl.NoteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/note")
public class NoteController {

    @Autowired
    NoteServiceImpl noteServiceImpl;


    @RequestMapping(value = "/create")
    public ResponseEntity<Note> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteServiceImpl.createNote(noteDTO));
    }

    @RequestMapping(value = "/update/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Note notas = noteServiceImpl.updateNote(id, noteDTO);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Nota atualizada com sucesso.",
                        "dados", notas
                )
        );
    }

//    @RequestMapping(value = "/list",
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    public List<Note> listNotes() {
//        return noteServiceImpl.listNotes();
//    }

    @RequestMapping(value = "/list/{idBlock}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> listNoteByBlock(@PathVariable("idBlock") Long idBlock) {
        List<Note> notes = noteServiceImpl.listNotesByBlock(idBlock);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Notas encontradas com sucesso.",
                        "dados", notes
                )
        );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
        noteServiceImpl.deleteNota(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }
}
