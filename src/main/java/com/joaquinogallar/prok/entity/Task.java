package com.joaquinogallar.prok.entity;

import com.joaquinogallar.prok.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public boolean isFromLast3Days() {
        long daysBetween = ChronoUnit.DAYS.between(this.createdAt, LocalDate.now());
        return daysBetween <= 3;
    }
}
