package com.apinote.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private BlocoDTO bloco;

    public NotaDTO(Long id, String titulo, String descricao, BlocoDTO bloco) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.bloco = bloco;
    }

    public NotaDTO() {
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

    public BlocoDTO getBloco() {
        return bloco;
    }

    public void setBloco(BlocoDTO bloco) {
        this.bloco = bloco;
    }
}
