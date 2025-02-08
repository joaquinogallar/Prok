package com.joaquinogallar.prok.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany
    private List<Task> tasks;

    private boolean enabled;

    public UserEntity() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
        enabled = true;
    }

}
