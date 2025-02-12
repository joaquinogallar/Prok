package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.repository.TaskRepository;
import com.joaquinogallar.prok.utils.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void markTaskAsCompleted(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("Task not found");

        task.setStatus(Status.FINISHED);
        task.setFinishedAt(LocalDate.now());
    }

    public void reDoTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("Task not found");

        Task newTask = new Task(task.getTitle(), task.getDescription(), LocalDate.now(), null, Status.ACTIVE, task.getLifeCycle() + 1);

        taskRepository.save(newTask);
        taskRepository.delete(task);
    }

}
