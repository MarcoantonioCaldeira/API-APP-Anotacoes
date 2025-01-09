package com.apinote.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private Set<BlocoDTO> blocos = new HashSet<>();

    public UsuarioDTO(Long id, String nome, String email, String senha, String confirmacaoSenha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "nome é obrigatório.")
    @NotNull(message = "nome é obrigatório.")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotBlank(message = "e-mail obrigatório.")
    @NotNull(message = "e-mail obrigatório.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "e-mail obrigatório.")
    @NotNull(message = "e-mail obrigatório.")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NotBlank(message = "e-mail obrigatório.")
    @NotNull(message = "e-mail obrigatório.")
    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public Set<BlocoDTO> getBlocos() {
        return blocos;
    }

    public void setBlocos(Set<BlocoDTO> blocos) {
        this.blocos = blocos;
    }
}
