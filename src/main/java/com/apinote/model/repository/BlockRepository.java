package com.apinote.model.repository;

import com.apinote.model.Block;
import com.apinote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    @Query("SELECT b FROM Block b WHERE b.user.id = :userId")
    List<Block> findByUser(@Param("userId") Long userId);
    
}
