package com.joaquinogallar.prok.auth.controller;

import com.joaquinogallar.prok.user.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.user.dto.UserEntityDto;
import com.joaquinogallar.prok.user.dto.UserLoginDto;
import com.joaquinogallar.prok.user.entity.UserEntity;
import com.joaquinogallar.prok.auth.service.AuthenticationService;
import com.joaquinogallar.prok.auth.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("userEntityRequestDto", new UserEntityRequestDto("", "", ""));
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute UserEntityRequestDto userEntityRequestDto,
                             BindingResult bindingResult,
                             Model model) {

        if(bindingResult.hasErrors()) {
            return "register";
        }

        try {
            UserEntityDto user = authenticationService.signUp(userEntityRequestDto);
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("error", "Error during registration");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showSignInForm(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto(null, null));
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute UserLoginDto user, Model model, HttpServletResponse response) {
        try {
            UserEntity authenticatedUser = authenticationService.authenticate(user);
            String jwtToken = jwtService.generateToken(authenticatedUser);

            Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600);
            response.addCookie(jwtCookie);

            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
