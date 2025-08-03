package com.joaquinogallar.prok.controller;


import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.JwtService;
import com.joaquinogallar.prok.service.TaskService;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    private final TaskService taskService;
    private final UserEntityService userEntityService;

    @Autowired
    public ViewController(TaskService taskService, UserEntityService userEntityService) {
        this.taskService = taskService;
        this.userEntityService = userEntityService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        List<Task> userTasks = userEntityService.findTasksByUserId(user.getId());

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
