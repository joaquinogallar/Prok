package com.joaquinogallar.prok.controller;


import com.joaquinogallar.prok.task.entity.Task;
import com.joaquinogallar.prok.user.entity.UserEntity;
import com.joaquinogallar.prok.task.service.TaskService;
import com.joaquinogallar.prok.user.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
