package com.joaquinogallar.prok.repository;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByUser(UserEntity user);

}
