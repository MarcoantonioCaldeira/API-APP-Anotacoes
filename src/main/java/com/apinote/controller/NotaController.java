package com.apinote.controller;

import com.apinote.model.Nota;
import com.apinote.model.Usuario;
import com.apinote.service.NotaService;
import com.apinote.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/usuario/{id_usuario}/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/criar",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> criarNota(@RequestBody Map<String, Object> payload) {
        try {
            String titulo = (String) payload.get("titulo");
            String descricao = (String) payload.get("descricao");
            Long usuarioId = Long.valueOf((Integer) payload.get("usuario_id"));

            Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

            Nota nota = new Nota();
            nota.setTitulo(titulo);
            nota.setDescricao(descricao);
            nota.setUsuario(usuario);

            Nota notaCriada = notaService.criarNota(nota);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of(
                            "mensagem", "Nota criada com sucesso!",
                            "dados", notaCriada
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "erro", "Erro ao criar a nota: " + e.getMessage()
                    )
            );
        }
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
