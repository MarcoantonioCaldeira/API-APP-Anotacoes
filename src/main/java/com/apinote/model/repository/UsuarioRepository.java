package com.apinote.model.repository;

import com.apinote.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from usuario u where u.email =:email")
    Optional<Usuario> findByEmail(@Param("email") String email);

}
