package com.joaquinogallar.prok.task.repository;

import com.joaquinogallar.prok.task.entity.Task;
import com.joaquinogallar.prok.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByUser(UserEntity user);

    List<Task> findByUserIdAndFinishedAtIsNotNull(UUID id);

    UUID id(Long id);
}
