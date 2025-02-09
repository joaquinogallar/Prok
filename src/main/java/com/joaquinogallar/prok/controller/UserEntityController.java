package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/users")
public class UserEntityController {

    private UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntityResponseDto>> getAllUsers() {
        List<UserEntityResponseDto> users = userEntityService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityResponseDto> getUserById(@PathVariable UUID id) {
        UserEntityResponseDto userEntity = userEntityService.getUserById(id);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntityRequestDto userEntityRequestDto) {
        userEntityService.createUser(userEntityRequestDto);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody UserEntityRequestDto userEntityRequestDto) {
        userEntityService.updateUser(id, userEntityRequestDto);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userEntityService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

}
