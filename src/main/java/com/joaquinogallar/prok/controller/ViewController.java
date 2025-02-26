package com.joaquinogallar.prok.controller;


import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    private final UserEntityService userEntityService;

    @Autowired
    public ViewController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userEntityRequestDto", new UserEntityRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute UserEntityRequestDto userEntityRequestDto, Model model) {
        try {
            UserEntityResponseDto user = userEntityService.createUser(userEntityRequestDto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            model.addAttribute("userEntityRequestDto", userEntityRequestDto);
            return "register";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
