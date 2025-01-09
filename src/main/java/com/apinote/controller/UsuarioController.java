package com.apinote.controller;

import com.apinote.model.Usuario;
import com.apinote.model.dto.UsuarioDTO;
import com.apinote.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "rest/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/criar",
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<Object> criarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario usuarioCriado  = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "mensagem", "Conexão bem-sucedida! Usuário criado com sucesso.",
                        "dados", usuarioCriado
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

    @GetMapping(value = "/listar",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Usuario> listarUsuario() {
        return usuarioService.listarUsuario();
    }
}
