package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
}
