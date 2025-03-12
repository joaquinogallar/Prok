package com.joaquinogallar.prok.entity;

import com.joaquinogallar.prok.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private int lifeCycle = 1;

    @CreationTimestamp
    private LocalDate createdAt = LocalDate.now();

    private LocalDate finishedAt = null;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Status status = Status.ACTIVE;

    public Task() {
        lifeCycle = 1;
        status = Status.ACTIVE;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
