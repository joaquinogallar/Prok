package com.joaquinogallar.prok.task.controller;

import com.joaquinogallar.prok.task.dto.TaskUpdateDto;
import com.joaquinogallar.prok.task.entity.Task;
import com.joaquinogallar.prok.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        String res = taskService.deleteTask(id);
        return ResponseEntity.ok(res);
    }

}
