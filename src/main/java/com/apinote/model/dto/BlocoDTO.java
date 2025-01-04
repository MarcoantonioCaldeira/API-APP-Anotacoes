package com.apinote.model.dto;

public class BlocoDTO {
    private Long id;
    private String titulo;
    private String descricao;

    public BlocoDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public BlocoDTO() {
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
