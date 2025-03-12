package com.joaquinogallar.prok.controller;


import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.JwtService;
import com.joaquinogallar.prok.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final JwtService jwtService;
    private final TaskService taskService;

    @Autowired
    public ViewController(JwtService jwtService, TaskService taskService) {
        this.jwtService = jwtService;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        List<Task> userTasks = taskService.getTasksByUser(user.getId());

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
            model.addAttribute("tasks", userTasks);
            model.addAttribute("numberOfTasks", userTasks.size());
            model.addAttribute("userId", user.getId());
        }

        return "home";
    }

}
