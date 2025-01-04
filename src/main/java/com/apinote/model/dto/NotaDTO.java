package com.apinote.model.dto;

public class NotaDTO {
    private Long id;
    private String titulo;
    private String descricao;

    public NotaDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public NotaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
