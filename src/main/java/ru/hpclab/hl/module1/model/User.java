package ru.hpclab.hl.module1.model;


import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;
public class User {

@Id
@GeneratedValue(strategy = GenerationType.UUID) // Автоматическая генерация UUID
@Column(name = "id")
@NonNull
private UUID identifier;
@Column(name = "name")
@NonNull
private String fio;
@Column(name = "mail")
@NonNull
private String email;
@Column(name = "date")
@NonNull
private LocalDateTime registrationDate;

    public User(@NonNull UUID identifier, @NonNull String fio, @NonNull String email) {
        this.identifier = identifier;
        this.fio = fio;
        this.email = email;
    }

    public User() {
    }

    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@NonNull UUID identifier) {
        this.identifier = identifier;
    }

    @NonNull
    public String getFio() {
        return fio;
    }

    public void setFio(@NonNull String fio) {
        this.fio = fio;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public LocalDateTime getRegistrationDate() { //getter for new field
        return registrationDate;
    }

    public void setRegistrationDate(@NonNull LocalDateTime registrationDate) { //setter for new field
        this.registrationDate = registrationDate;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
