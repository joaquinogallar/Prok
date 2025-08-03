package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}
