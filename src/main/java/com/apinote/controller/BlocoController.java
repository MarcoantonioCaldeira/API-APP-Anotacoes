package com.apinote.controller;

import com.apinote.model.Bloco;
import com.apinote.model.dto.BlocoDTO;
import com.apinote.service.BlocoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/usuarios/bloco")
public class BlocoController {

    @Autowired
    BlocoService blocoService;

    @RequestMapping(value = "/criar")
    public ResponseEntity<Bloco> criarBloco(@Valid @RequestBody BlocoDTO blocoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blocoService.criarBloco(blocoDTO));
    }


    @RequestMapping(value = "/atualizar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody BlocoDTO bloco) {
        Bloco blocos = blocoService.atualizarBloco(id, bloco);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco atualizado com sucesso.",
                        "dados", blocos
                )
        );
    }

    @RequestMapping(value = "/listar",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Bloco> listarBlocos() {
        return blocoService.listarBloco();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletarBloco(@PathVariable("id") Long id) {
        blocoService.deletarBloco(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco deletado com sucesso."
                )
        );
    }
}
