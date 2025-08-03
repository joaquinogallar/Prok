package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.dto.TaskUpdateDto;
import com.joaquinogallar.prok.dto.UserEntityDto;
import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.repository.TaskRepository;
import com.joaquinogallar.prok.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder;

    private final TaskRepository taskRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, TaskRepository taskRepository) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
    }

    public List<UserEntityDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserEntityDto> userEntityDtos = new ArrayList<>();
        users.forEach(userEntity -> {
            UserEntityDto userEntityDto = new UserEntityDto(userEntity);
            userEntityDtos.add(userEntityDto);
        });
        return userEntityDtos;
    }

    public UserEntityDto getUserById(UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("User not found");

        return new UserEntityDto(user);
    }

    // ------------------------------------------------------------------------------------------
    @Transactional
    public void createTask(UUID userId, Task task) {
        if(task.getTitle() == null || task.getTitle().isEmpty()) {
            return;
        }

        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        task.setUser(user);
        user.getTasks().add(task);

        userEntityRepository.save(user);
    }

    @Transactional
    public void deleteTask(UUID userId, Long taskId) {
        UserEntity user = userEntityRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.getTasks().remove(taskRepository.findById(taskId).orElse(null));

        userEntityRepository.save(user);
    }

    public String deleteUser(UUID id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return "User not found";
        }
        userEntityRepository.delete(userEntity);
        return "User deleted";
    }

    public String updateTask(UUID userId, Long taskId, TaskUpdateDto dataTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setTitle(dataTask.title());
        task.setDescription(dataTask.description());
        taskRepository.save(task);
        return "Task updated";
    }

    public List<Task> findTasksByUserId(UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getTasks();
    }

    public List<Task> findFinishedTasksByUserId(UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getTasks().stream()
                .filter(t -> t.getFinishedAt() != null)
                .toList();
    }
}
