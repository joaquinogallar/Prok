package com.joaquinogallar.prok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class UserEntityRequestDto {

    private String fullName;
    private String email;
    private String password;
}
