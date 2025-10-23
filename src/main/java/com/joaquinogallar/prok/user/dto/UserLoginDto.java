package com.joaquinogallar.prok.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserLoginDto(
        @NotBlank(message = "Email cannot be empty.")
        String email,

        @NotBlank(message = "Password cannot be empty.")
        String password
) {
}