package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.TaskUpdateDto;
import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class UserEntityViewController {
    private UserEntityService userEntityService;

    @Autowired
    public void setUserEntityService(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/my-account")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (authentication.isAuthenticated()) {
            List<Task> userTasks = userEntityService.findTasksByUserId(user.getId());
            List<Task> userFinishedTasks = userEntityService.findFinishedTasksByUserId(user.getId());

            model.addAttribute("numberOfTasks", userTasks.size());
            model.addAttribute("user", user);
            model.addAttribute("tasks", userTasks);
            model.addAttribute("tasksFinished", userFinishedTasks);
        }

        return "userProfile";
    }

    @PostMapping("/users/{userId}/tasks")
    public String createTasks(@PathVariable UUID userId, @ModelAttribute Task task) {
        userEntityService.createTask(userId, task);
        return "redirect:/home";
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public String deleteTask(@PathVariable UUID userId, @PathVariable Long taskId) {
        userEntityService.deleteTask(userId, taskId);
        return "redirect:/home";
    }

    @PutMapping("/users/{userId}/tasks/{taskId}")
    public String updateTask(@PathVariable UUID userId, @PathVariable Long taskId, @ModelAttribute TaskUpdateDto task) {
        userEntityService.updateTask(userId, taskId, task);
        return "redirect:/home";
    }
}
