package com.joaquinogallar.prok.entity;

import com.joaquinogallar.prok.utils.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    private LocalDate createdAt;
    private LocalDate finishedAt;

    private Status status;

    public Task() {
        createdAt = LocalDate.now();
        finishedAt = LocalDate.now();
    }

}
