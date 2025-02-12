package com.apinote.model.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class UserDTO {
    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;

    public List<BlockDTO> getBloco() {
        return bloco;
    }

    private List<BlockDTO> bloco;

    public UserDTO(String nome, String email, String senha, String confirmacaoSenha, List<BlockDTO> bloco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        this.bloco = bloco;
    }

    public UserDTO() {
    }

    @NotBlank(message = "O nome do usuario é obrigatório.")
//    @NotNull(message = "O nome do usuario é obrigatório.")
    public String getNome() {
        return nome;
    }

    @NotBlank(message = "e-mail obrigatório.")
    public String getEmail() {
        return email;
    }

    @NotBlank(message = "A senha é obrigatoria")
    public String getSenha() {
        return senha;
    }

    @NotBlank(message = "O campo é obrigatorio")
    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

}
