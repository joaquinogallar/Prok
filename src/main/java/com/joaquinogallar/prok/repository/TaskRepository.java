package com.joaquinogallar.prok.repository;

import com.joaquinogallar.prok.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
