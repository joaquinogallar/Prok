package com.joaquinogallar.prok.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    @Builder.Default
    private LocalDate updatedAt = LocalDate.now();

    @OneToMany
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @Builder.Default
    private boolean enabled = true;

    private Role role;

    public UserEntity(String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}