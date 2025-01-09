package com.apinote.controller;

import com.apinote.model.Bloco;
import com.apinote.model.Nota;
import com.apinote.model.dto.BlocoDTO;
import com.apinote.model.dto.NotaDTO;
import com.apinote.service.BlocoService;
import com.apinote.service.NotaService;
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
@RequestMapping(value = "rest/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @Autowired
    BlocoService blocoService;

    @RequestMapping(value = "/criar",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> criarNota(@RequestBody Map<String, Object> payload) {
        try {
            String titulo = (String) payload.get("titulo");
            String descricao = (String) payload.get("descricao");
            Long blocoId = Long.valueOf((Integer) payload.get("bloco_id"));

            BlocoDTO bloco = blocoService.buscarBlocoPorId(blocoId);

            NotaDTO nota = new NotaDTO();
            nota.setTitulo(titulo);
            nota.setDescricao(descricao);
            nota.setBloco(bloco);

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
