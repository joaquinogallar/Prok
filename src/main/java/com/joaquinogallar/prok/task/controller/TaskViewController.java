package com.joaquinogallar.prok.task.controller;

import com.joaquinogallar.prok.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class TaskViewController {
    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/{taskId}/completed")
    public String markTaskAsCompleted(@PathVariable Long taskId) {
        taskService.markTaskAsCompleted(taskId);
        return "redirect:/home";
    }
}
