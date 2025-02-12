package com.apinote.model.dto;

public class BlockDTO {
    private String titulo;
    private String descricao;
    private Long usuarioId;

    public BlockDTO(String titulo, String descricao, Long usuarioId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public BlockDTO(){}

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
