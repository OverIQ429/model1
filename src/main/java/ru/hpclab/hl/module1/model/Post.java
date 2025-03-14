package ru.hpclab.hl.module1.model;


import org.springframework.lang.NonNull;
import jakarta.persistence.*;
import ru.hpclab.hl.module1.Entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;


public class Post {

@Id
@GeneratedValue(strategy = GenerationType.UUID) // Автоматическая генерация UUID
@Column(name = "id")
private UUID identifier;

@Column(name = "text")
@NonNull
private String text; // Исправлено название поля

@Column(name = "publication_date") // Исправлено название столбца
@NonNull
private LocalDateTime publicationDate; // Исправлено название поля

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "owner_id", nullable = false) // Исправлено название столбца
private UserEntity owner;

    public Post(@NonNull UUID identifier, @NonNull String Text, UserEntity ownerId) {
        this.identifier = identifier;
        this.text = Text;
        this.owner = ownerId;
    }

    public Post() {
    }

    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NonNull UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String Text) {
        this.text = Text;
    }

    @NonNull
    public UserEntity getOwnerID() {
        return owner;
    }

    public void setOwnerID(@NonNull UserEntity Text) {
        this.owner = Text;
    }

    @NonNull
    public LocalDateTime getPublicationDate() { //getter for new field
        return publicationDate;
    }

    public void setPublicationDate(@NonNull LocalDateTime publicationDate) { //setter for new field
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "identifier=" + identifier +
                ", text='" + text + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
