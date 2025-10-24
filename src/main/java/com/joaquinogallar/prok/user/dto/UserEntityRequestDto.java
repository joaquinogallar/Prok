package com.joaquinogallar.prok.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


public record UserEntityRequestDto(

        @NotBlank(message = "Fullname cannot be empty.")
        String fullName,

        @NotBlank(message = "Email cannot be empty.")
        @Email(message = "The email is incorrect.")
        String email,

        @NotBlank(message = "Password cannot be empty.")
        @Size(min = 8, message = "Password must be at least 6 characters long.")
        String password
) {}