package com.apinote.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="block")
@EqualsAndHashCode(of = "id")
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_block")
    private Long id;
    private String title;
    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "block")
    private Set<Note> notes = new HashSet<>();

    public Block(Long id, String title, String description, User user, Set<Note> notes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.notes = notes;
    }

    public Block() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title_block")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description_block")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Column(name = "notes_block")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
