package com.apinote.model.repository;

import com.apinote.model.Block;
import com.apinote.model.Note;
import com.apinote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.block.id = :blockId")
    List<Note> findByBlock(@Param("blockId")  Long blockId);
}
