package com.joaquinogallar.prok.dto;

import com.joaquinogallar.prok.entity.Task;
import com.joaquinogallar.prok.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserEntityResponseDto {

    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    private LocalDate createdAt;

    public UserEntityResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.createdAt = userEntity.getCreatedAt();
    }
}
