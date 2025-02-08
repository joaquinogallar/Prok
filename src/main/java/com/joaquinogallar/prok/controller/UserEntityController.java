package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserEntityController {

    private UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    public ResponseEntity<List<UserEntityResponseDto>> getAllUsers() {
        List<UserEntityResponseDto> users = userEntityService.getAllUsers();
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<UserEntityResponseDto> getUserById(Long id) {}

}
