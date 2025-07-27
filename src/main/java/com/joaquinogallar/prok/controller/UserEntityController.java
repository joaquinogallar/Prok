package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/users")
public class UserEntityController {

    private final UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserEntityResponseDto>> getAllUsers() {
        List<UserEntityResponseDto> users = userEntityService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserEntityResponseDto> getUserById(@PathVariable UUID id) {
        UserEntityResponseDto userEntity = userEntityService.getUserById(id);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        String message = userEntityService.deleteUser(id);

        if (message.equals("User not found")) return new ResponseEntity<>(message, HttpStatus.NOT_FOUND); // 404

        return new ResponseEntity<>(message, HttpStatus.OK); // 200
    }

    // tasks
    @PostMapping("/{userId}/tasks")
    public String addTask(
            @PathVariable UUID userId,
            @ModelAttribute Task task,
            RedirectAttributes redirectAttributes
    ) {
        userEntityService.createTask(userId, task);
        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully");
        return "redirect:/home";
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public String deleteTask(
            @PathVariable UUID userId,
            @PathVariable Long taskId,
            RedirectAttributes redirectAttributes
    ) {
        userEntityService.deleteTask(userId, taskId);
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully");
        return "redirect:/home";
    }
}
