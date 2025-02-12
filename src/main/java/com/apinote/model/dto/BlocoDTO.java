package com.apinote.model.dto;
import com.apinote.model.Bloco;

public class BlocoDTO {
    private String titulo;
    private String descricao;
    private Long usuarioId;

    public BlocoDTO(String titulo, String descricao, Long usuarioId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public BlocoDTO(){}

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
