package com.apinote.controller;

import com.apinote.model.Block;
import com.apinote.model.dto.BlockDTO;
import com.apinote.service.BlockService;
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
public class BlockController {

    @Autowired
    BlockService blockService;

    @RequestMapping(value = "/criar")
    public ResponseEntity<Block> criarBloco(@Valid @RequestBody BlockDTO blockDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockService.criarBloco(blockDTO));
    }


    @RequestMapping(value = "/atualizar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody BlockDTO bloco) {
        Block blocos = blockService.atualizarBloco(id, bloco);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco atualizado com sucesso.",
                        "dados", blocos
                )
        );
    }

    @RequestMapping(value = "/listar",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Block> listarBlocos() {
        return blockService.listarBloco();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletarBloco(@PathVariable("id") Long id) {
        blockService.deletarBloco(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco deletado com sucesso."
                )
        );
    }
}
