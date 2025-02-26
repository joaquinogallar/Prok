package com.joaquinogallar.prok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntityRequestDto {

    private String fullName;
    private String email;
    private String password;
}
