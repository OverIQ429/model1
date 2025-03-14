package ru.hpclab.hl.module1.Entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "text")
    private String text;

    @Column(name = "publication_date")
    @NotNull
    private LocalDateTime publicationDate;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity userEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public UserEntity getOwner() {
        return userEntity;
    }

    public void setOwner(UserEntity owner) {
        this.userEntity = owner;
    }
}
