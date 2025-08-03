package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.TaskUpdateDto;
import com.joaquinogallar.prok.dto.UserEntityDto;
import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllUsers() {
        List<UserEntityDto> users = userEntityService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        UserEntityDto userEntity = userEntityService.getUserById(id);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        String message = userEntityService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    // tasks
    @PostMapping("/{userId}/tasks")
    @ResponseBody
    public ResponseEntity<?> addTask(
            @PathVariable UUID userId,
            @ModelAttribute Task task) {
        userEntityService.createTask(userId, task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(
            @PathVariable UUID userId,
            @PathVariable Long taskId,
            RedirectAttributes redirectAttributes
    ) {
        userEntityService.deleteTask(userId, taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted");
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    @ResponseBody
    public ResponseEntity<?> updateTask(@PathVariable UUID userId, @PathVariable Long taskId, @ModelAttribute TaskUpdateDto task) {
        userEntityService.updateTask(userId, taskId, task);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated");
    }
}
