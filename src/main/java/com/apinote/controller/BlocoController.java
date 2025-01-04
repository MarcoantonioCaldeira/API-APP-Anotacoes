package com.apinote.controller;

import com.apinote.model.Bloco;
import com.apinote.model.Usuario;
import com.apinote.service.BlocoService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/usuarios/bloco")
public class BlocoController {
    @Autowired
    BlocoService blocoService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/criar",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> criarBloco(@RequestBody Map<String, Object> payload) {
        try {
            String titulo = (String) payload.get("titulo");
            String descricao = (String) payload.get("descricao");
            Long usuarioId = Long.valueOf((Integer) payload.get("usuario_id"));

            Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

            Bloco bloco = new Bloco();
            bloco.setTitulo(titulo);
            bloco.setDescricao(descricao);
            bloco.setUsuario(usuario);

            Bloco blocoCriado = blocoService.criarBloco(bloco);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of(
                            "mensagem", "Bloco criado com sucesso!",
                            "dados", blocoCriado
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "erro", "Erro ao criar a bloco: " + e.getMessage()
                    )
            );
        }
    }


    @RequestMapping(value = "/atualizar/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public  ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody Bloco bloco) {
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
