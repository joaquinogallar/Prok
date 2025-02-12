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
    private int lifeCycle;

    private LocalDate createdAt;
    private LocalDate finishedAt;

    private Status status;

    public Task() {
        createdAt = LocalDate.now();
        lifeCycle = 1;
        status = Status.ACTIVE;
        finishedAt = null;
    }

    public Task(String title, String description, LocalDate createdAt, LocalDate finishedAt, Status status, int lifeCycle) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.status = status;
    }

}
