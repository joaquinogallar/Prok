package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.service.TaskService;
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
