package com.apinote.service;

import com.apinote.model.Bloco;
import com.apinote.model.Usuario;
import com.apinote.model.dto.BlocoDTO;
import com.apinote.model.repository.BlocoRepository;
import com.apinote.model.repository.UsuarioRepository;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BlocoService {

    @Autowired
    BlocoRepository blocoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityConversor entityConversor;


    public Bloco criarBloco(BlocoDTO blocoDTO) {
        Bloco bloco = entityConversor.parseObject(blocoDTO, Bloco.class);

        if (bloco.getUsuario() == null || bloco.getUsuario().getId() == null) {
            throw new IllegalArgumentException("O ID do bloco deve ser informado.");
        }

        Usuario usuario = usuarioRepository.findById(blocoDTO.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Bloco não encontrado"));

        bloco.setUsuario(usuario);

        return  blocoRepository.save(bloco);
    }


    public Bloco atualizarBloco(Long id, BlocoDTO entity) {
        Bloco blocoAtualizado = entityConversor.parseObject(blocoRepository.findById(id).get(), Bloco.class);
        blocoAtualizado.setTitulo(entity.getTitulo());
        blocoAtualizado.setDescricao(entity.getDescricao());
        blocoAtualizado = blocoRepository.saveAndFlush(blocoAtualizado);
        return blocoAtualizado;
    }

    public List<Bloco> listarBloco() {
        List<Bloco> blocos = blocoRepository.findAll();
        return blocos;
    }


    public void deletarBloco(Long id) {
        try {
            blocoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar bloco: " + e.getMessage());
        }
    }

    public BlocoDTO buscarBlocoPorId(Long id) {
        Bloco bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloco não encontrado com o ID: " + id));
        return entityConversor.parseObject(bloco, BlocoDTO.class);
    }
}
