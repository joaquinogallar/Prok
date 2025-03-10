package com.joaquinogallar.prok.controller;

import com.joaquinogallar.prok.dto.LoginDto;
import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.dto.UserLoginDto;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.service.AuthenticationService;
import com.joaquinogallar.prok.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntityResponseDto> createUser(@RequestBody UserEntityRequestDto userEntityRequestDto) {
        UserEntityResponseDto user = authenticationService.signUp(userEntityRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> authenticate(@RequestBody UserLoginDto user) {
        System.out.println("IN AUTHENTICATE");

        UserEntity authenticatedUser = authenticationService.authenticate(user);

        System.out.println("USER="+authenticatedUser);


        String jwtToken = jwtService.generateToken(authenticatedUser);

        System.out.println("TOKEN="+jwtToken);

        LoginDto loginResponse = new LoginDto(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
