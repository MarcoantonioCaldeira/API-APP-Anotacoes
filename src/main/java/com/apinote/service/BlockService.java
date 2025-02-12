package com.apinote.service;

import com.apinote.model.Block;
import com.apinote.model.User;
import com.apinote.model.dto.BlockDTO;
import com.apinote.model.repository.BlockRepository;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Block criarBloco(BlockDTO blockDTO) {

        User user = userRepository.findById(blockDTO.getUsuarioId())
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));

        Block block = new Block();
        block.setTitulo(blockDTO.getTitulo());
        block.setDescricao(blockDTO.getDescricao());
        block.setUsuario(user);

        return blockRepository.save(block);
    }


    public Block atualizarBloco(Long id, BlockDTO entity) {
        Block blockAtualizado = entityConversor.parseObject(blockRepository.findById(id).get(), Block.class);
        blockAtualizado.setTitulo(entity.getTitulo());
        blockAtualizado.setDescricao(entity.getDescricao());
        blockAtualizado = blockRepository.saveAndFlush(blockAtualizado);
        return blockAtualizado;
    }

    public List<Block> listarBloco() {
        List<Block> blocks = blockRepository.findAll();
        return blocks;
    }


    public void deletarBloco(Long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o : " + id));
        blockRepository.deleteById(id);
    }

    public BlockDTO buscarBlocoPorId(Long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o ID: " + id));
        return entityConversor.parseObject(block, BlockDTO.class);
    }
}
