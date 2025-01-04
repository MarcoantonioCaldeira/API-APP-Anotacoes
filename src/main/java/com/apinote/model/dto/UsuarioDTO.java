package com.apinote.model.dto;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;

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

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }
}
