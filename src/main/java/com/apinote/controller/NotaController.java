package com.apinote.controller;

import com.apinote.model.Nota;
import com.apinote.model.dto.NotaDTO;
import com.apinote.service.BlocoService;
import com.apinote.service.NotaService;
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
public class NotaController {

    @Autowired
    NotaService notaService;


    @RequestMapping(value = "/criar")
    public ResponseEntity<Nota> criarNota(@Valid @RequestBody NotaDTO notaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.criarNota(notaDTO));
    }

    @RequestMapping(value = "/atualizar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        Nota notas = notaService.atualizarNota(id, nota);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Nota atualizada com sucesso.",
                        "dados", notas
                )
        );
    }

    @RequestMapping(value = "/listar",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Nota> listarNotas() {
        return notaService.listarNotas();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletarNota(@PathVariable("id") Long id) {
        notaService.deletarNota(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }
}
