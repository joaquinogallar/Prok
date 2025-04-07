package com.joaquinogallar.prok.repository;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByUser(UserEntity user);

    List<Task> findByUserIdAndFinishedAtIsNotNull(UUID id);

    UUID id(Long id);
}
