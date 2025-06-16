package com.apinote.web.controller;

import com.apinote.model.Block;
import com.apinote.model.Note;
import com.apinote.model.dto.BlockDTO;
import com.apinote.service.impl.BlockServiceImpl;
import com.apinote.web.SystemMessage;
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

@RestController
@RequestMapping(value = "rest/user/block")
public class BlockController {

    @Autowired
    BlockServiceImpl blockServiceImpl;

    @RequestMapping(value = "/create")
    public ResponseEntity<Block> createBlock(@Valid @RequestBody BlockDTO blockDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockServiceImpl.createBlock(blockDTO));
    }


    @RequestMapping(value = "/update/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> updateBlock(@PathVariable Long id, @RequestBody BlockDTO blockDTO) {
        Block blocks = blockServiceImpl.updateBlock(id, blockDTO);
        SystemMessage<Block> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota criada com sucesso.", blocks);
        return ResponseEntity.ok().body(userMessage);
    }

    @RequestMapping(value = "/list/{idUser}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> listBlockByUser(@PathVariable("idUser") Long idUser) {
        List<Block> blocks = blockServiceImpl.listBlockByUser(idUser);
        SystemMessage<List<Block>> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Nota criada com sucesso.", blocks);
        return ResponseEntity.ok().body(userMessage);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteBlock(@PathVariable("id") Long id) {
        blockServiceImpl.deleteBlock(id);
        SystemMessage<Note> userMessage = new SystemMessage<>(HttpStatus.OK.value(), "Conexão bem-sucedida! Bloco deletado com sucesso", null);
        return ResponseEntity.ok().body(userMessage);
    }
}
