package com.apinote.service.impl;

import com.apinote.model.Block;
import com.apinote.model.User;
import com.apinote.model.dto.BlockDTO;
import com.apinote.model.repository.BlockRepository;
import com.apinote.model.repository.UserRepository;
import com.apinote.service.BlockService;
import com.apinote.service.exceptions.BlockNotFoundException;
import com.apinote.service.exceptions.UserNotFoundException;
import com.apinote.service.mapper.EntityConversor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityConversor entityConversor;

    @Transactional
    public Block createBlock(BlockDTO blockDTO) {

        User user = userRepository.findById(blockDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));

        Block block = new Block();
        block.setTitle(blockDTO.title());
        block.setDescription(blockDTO.description());
        block.setUser(user);

        return blockRepository.save(block);
    }


    public Block updateBlock(Long id, BlockDTO entity) {
        Block blockAtualizado = entityConversor.parseObject(blockRepository.findById(id), Block.class);
        blockAtualizado.setTitle(entity.title());
        blockAtualizado.setDescription(entity.description());
        blockAtualizado = blockRepository.saveAndFlush(blockAtualizado);
        return blockAtualizado;
    }

    public List<Block> listBlock() {
        List<Block> blocks = blockRepository.findAll();
        return blocks;
    }


    public void deleteBlock(Long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o : " + id));
        blockRepository.deleteById(id);
    }

    public BlockDTO searchBlockById(Long id) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new BlockNotFoundException("Bloco não encontrado com o ID: " + id));
        return entityConversor.parseObject(block, BlockDTO.class);
    }

    public List<Block> listBlockByUser(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + idUser));
        return blockRepository.findByUser(idUser);
    }
}
