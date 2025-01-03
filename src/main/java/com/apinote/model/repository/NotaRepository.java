package com.apinote.model.repository;

import com.apinote.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
