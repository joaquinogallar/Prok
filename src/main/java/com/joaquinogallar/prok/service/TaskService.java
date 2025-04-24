package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.repository.TaskRepository;
import com.joaquinogallar.prok.repository.UserEntityRepository;
import com.joaquinogallar.prok.utils.Status;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserEntityRepository userEntityRepository) {
        this.taskRepository = taskRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("Task not found");

        return task;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElse(null);
        if (taskToUpdate == null) throw new EntityNotFoundException("Task not found");

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());

        return taskRepository.save(taskToUpdate);
    }

    public String deleteTask(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElse(null);
        if (taskToDelete == null) return "Task not found";

        taskRepository.delete(taskToDelete);

        return "Task deleted";
    }

    public void markTaskAsCompleted(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("Task not found");

        if(task.getFinishedAt() != null) {
            task.setStatus(Status.ACTIVE);
            task.setFinishedAt(null);
        } else {
            task.setStatus(Status.FINISHED);
            task.setFinishedAt(LocalDate.now());
        }
        taskRepository.save(task);
    }

    public void reDoTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("Task not found");

        Task newTask = new Task(task.getTitle(), task.getDescription());
        newTask.setLifeCycle(task.getLifeCycle() + 1);

        taskRepository.save(newTask);
        taskRepository.delete(task);
    }

    public List<Task> getTasksByUser(UUID idUser) {
        UserEntity user = userEntityRepository.findById(idUser).orElse(null);
        if (user == null) throw new EntityNotFoundException("User not found");

        return user.getTasks();
    }

    public List<Task> getFinishedTasksByUser(UUID idUser) {
        List<Task> tasks = taskRepository.findByUserIdAndFinishedAtIsNotNull(idUser);
        return tasks;
    }

}
