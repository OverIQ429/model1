package ru.hpclab.hl.module1.model;

import org.springframework.lang.NonNull;
import jakarta.persistence.*;
import ru.hpclab.hl.module1.Entity.PostEntity;
import ru.hpclab.hl.module1.Entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Likes {

@Id
@GeneratedValue(strategy = GenerationType.UUID) // Автоматическая генерация UUID
@Column(name = "id")
@NonNull
private UUID Id;

private LocalDateTime publication_date;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "userPostId", nullable = false)
private PostEntity userPostId;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private UserEntity user_id;


    public Likes(  @NonNull UUID Id, @NonNull PostEntity userPostId, @NonNull UserEntity user_id) {
        this.Id = Id;
        this.userPostId = userPostId;
        this.user_id = user_id;
    }

    public Likes() {
   }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID Id) {
        this.Id = Id;
    }

    public UserEntity getUserId() {
        return user_id;
    }

    public void setUserId(UserEntity userId) {
        this.user_id = userId;
    }

    public PostEntity getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(PostEntity userPostId) {
        this.userPostId = userPostId;
    }



    @NonNull
    public LocalDateTime getPublicationDate() { //getter for new field
        return publication_date;
    }

    public void setPublicationDate(@NonNull LocalDateTime publicationDate) { //setter for new field
        this.publication_date = publicationDate;
    }

    @Override
    public String toString() {
        return "Likes{" +
                ", post=" + userPostId + '\'' +
                ", publicationDate=" + publication_date +
                '}';
    }

}
