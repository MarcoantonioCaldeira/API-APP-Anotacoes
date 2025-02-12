package com.apinote.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoteDTO {
    private String titulo;
    private String descricao;
    private Long blocoId;

    public NoteDTO(String titulo, String descricao, Long blocoId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.blocoId = blocoId;
    }

    public NoteDTO() {
    }

    @NotBlank(message = "O titulo é obrigatorio")
    public String getTitulo() {
        return titulo;
    }

    @NotBlank(message = "A descrição é obrigatorio")
    public String getDescricao() {
        return descricao;
    }

    @NotNull(message = "O id do bloco referente é obrigatorio")
    public Long getBlocoId() {
        return blocoId;
    }

}
