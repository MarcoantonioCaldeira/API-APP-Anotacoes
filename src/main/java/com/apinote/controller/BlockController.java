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
@RequestMapping(value = "rest/user/block")
public class BlockController {

    @Autowired
    BlockService blockService;

    @RequestMapping(value = "/create")
    public ResponseEntity<Block> createBlock(@Valid @RequestBody BlockDTO blockDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockService.createBlock(blockDTO));
    }


    @RequestMapping(value = "/update/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> updateBlock(@PathVariable Long id, @RequestBody BlockDTO blockDTO) {
        Block blocks = blockService.updateBlock(id, blockDTO);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco atualizado com sucesso.",
                        "dados", blocks
                )
        );
    }

    @RequestMapping(value = "/list",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Block> listBlocks() {
        return blockService.listBlock();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteBlock(@PathVariable("id") Long id) {
        blockService.deleteBlock(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Bloco deletado com sucesso."
                )
        );
    }
}
