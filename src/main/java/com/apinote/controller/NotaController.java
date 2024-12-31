package com.apinote.controller;

import com.apinote.model.Nota;
import com.apinote.service.NotaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "rest/usuario/{id_usuario}/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @RequestMapping(value = "/criar",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> criarNota(@RequestBody Nota nota) {
        Nota notas = notaService.criarNota(nota);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário criado com sucesso.",
                        "dados", notas
                )
        );
    }

    @RequestMapping(value = "/atualizar/{id}")
    public void atualizarNota(@RequestBody Long id) {
        notaService.atualizarNota(id);
    }

    @RequestMapping(value = "/listar")
    public void listarNotas() {
        notaService.listarNotas();
    }

    @DeleteMapping(value = "/deletar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> deletarNota(@PathParam("id") Long id) {
        notaService.deletarNota(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }
}
