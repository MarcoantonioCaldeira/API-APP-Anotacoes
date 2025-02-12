package com.apinote.controller;

import com.apinote.model.Note;
import com.apinote.model.dto.NoteDTO;
import com.apinote.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/nota")
public class NoteController {

    @Autowired
    NoteService noteService;


    @RequestMapping(value = "/criar")
    public ResponseEntity<Note> criarNota(@Valid @RequestBody NoteDTO noteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.criarNota(noteDTO));
    }

    @RequestMapping(value = "/atualizar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody Note note) {
        Note notas = noteService.atualizarNota(id, note);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Nota atualizada com sucesso.",
                        "dados", notas
                )
        );
    }

    @RequestMapping(value = "/listar",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Note> listarNotas() {
        return noteService.listarNotas();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletarNota(@PathVariable("id") Long id) {
        noteService.deletarNota(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }
}
