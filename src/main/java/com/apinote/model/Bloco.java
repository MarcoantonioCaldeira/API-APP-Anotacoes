package com.apinote.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="bloco")
public class Bloco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloco")
    private Long id;
    private String titulo;
    private String descricao;

    private Set<Nota> notas = new HashSet<>();;

    public Bloco(Long id, String titulo, String descricao, Set<Nota> notas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.notas = notas;
    }

    public Bloco() {

    }

    @Column(name = "id_bloco")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "titulo_bloco")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "descricao_bloco")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

}
