package com.apinote.model.repository;

import com.apinote.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//    @Query("insert into usuario (nome_usuario, email_usuario, senha_usuario) values (:nome, :email, :senha)")
//    void criarUsuario(String nome, String email, String senha);
//
//    @Query("delete from usuario where id_usuario = :id")
//    void deletarUsuario(Long id);

}
