package com.joaquinogallar.prok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserEntityRequestDto {

    private String fullName;
    private String email;
    private String password;
}
