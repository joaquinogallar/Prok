package com.joaquinogallar.prok.dto;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserEntityRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
