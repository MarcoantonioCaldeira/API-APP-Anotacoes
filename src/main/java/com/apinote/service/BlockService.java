package com.apinote.service;

import com.apinote.model.Block;
import com.apinote.model.dto.BlockDTO;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface BlockService {
    public Block createBlock(BlockDTO blockDTO);
    public Block updateBlock(Long id, BlockDTO entity);
    public void deleteBlock(Long id);
    public List<Block> listBlockByUser(Long idUser);
}
