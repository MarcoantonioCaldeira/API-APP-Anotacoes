package com.apinote.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BlocoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private UsuarioDTO usuario;

    public BlocoDTO(Long id, String titulo, String descricao, UsuarioDTO usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao  = descricao;
        this.usuario = usuario;
    }

    public BlocoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "O titulo é obrigatorio")
    @NotNull(message = "O titulo é obrigatorio")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotBlank(message = "A descrição é obrigatoria")
    @NotNull(message = "A descrição é obrigatoria")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Bloco [ " + " titulo=" + titulo + " descricao=" + descricao + ", usuario=" + usuario + "]";
    }
}
