package com.apinote.service;

import com.apinote.model.Bloco;
import com.apinote.model.Usuario;
import com.apinote.model.dto.BlocoDTO;
import com.apinote.model.repository.BlocoRepository;
import com.apinote.model.repository.UsuarioRepository;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocoService {

    @Autowired
    BlocoRepository blocoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Bloco criarBloco(BlocoDTO blocoDTO) {

        Usuario usuario = usuarioRepository.findById(blocoDTO.getUsuarioId())
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));

        Bloco bloco = new Bloco();
        bloco.setTitulo(blocoDTO.getTitulo());
        bloco.setDescricao(blocoDTO.getDescricao());
        bloco.setUsuario(usuario);

        return blocoRepository.save(bloco);
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
        Bloco bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o : " + id));
        blocoRepository.deleteById(id);
    }

    public BlocoDTO buscarBlocoPorId(Long id) {
        Bloco bloco = blocoRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o ID: " + id));
        return entityConversor.parseObject(bloco, BlocoDTO.class);
    }
}
