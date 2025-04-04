package com.joaquinogallar.prok.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class UserEntityRequestDto {

    @NotBlank(message = "Fullname cannot be empty.")
    private String fullName;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "The email is incorrect.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 6 characters long.")
    private String password;
}
