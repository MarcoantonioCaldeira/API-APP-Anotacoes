package com.apinote.controller;

import com.apinote.model.Usuario;
import com.apinote.service.UsuarioService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping( value = "rest/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/criar",
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário criado com sucesso.",
                        "dados", usuario1
                )
        );
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok().body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário deletado com sucesso."
                )
        );
    }
}
