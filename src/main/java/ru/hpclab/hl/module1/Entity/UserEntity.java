package ru.hpclab.hl.module1.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID identifier;

    @Column(name = "fio")
    private String fio;
    @Column(name = "email")
    private String email;
    @Column(name = "registrationDate")
    private LocalDateTime registrationDate;
    @Column(name = "selflikes")
    private Integer SelfLikes;
    public UserEntity() {
    }

    public UserEntity( UUID identifier, String fio, String email, LocalDateTime registrationDate) {
        this.identifier = identifier;
        this.fio = fio;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return identifier;
    }

    public void setId(UUID id) {
        this.identifier = id;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getSelfLikes() {
        return SelfLikes;
    }

    public void setSelfLikes(Integer value) {
        SelfLikes = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + identifier +
                ", fio='" + fio + '\'' +
                ", mail=" + email +
                ", likes=" + SelfLikes +
                '}';
    }
}