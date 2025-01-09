package com.apinote.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta. persistence. ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta. persistence. FetchType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="bloco")
public class Bloco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloco")
    private Long id;
    private String titulo;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    //@JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "bloco", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private Set<Nota> notas = new HashSet<>();

    public Bloco(Long id, String titulo, Usuario usuario, Set<Nota> notas) {
        this.id = id;
        this.titulo = titulo;
        this.usuario = usuario;
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

    @Column(name = "notas_bloco")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)

            return true;

        if (obj == null)

            return false;

        if (getClass() != obj.getClass())

            return false;

        Bloco other = (Bloco) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
