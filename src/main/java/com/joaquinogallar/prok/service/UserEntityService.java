package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserEntityService {

    private UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public List<UserEntityResponseDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserEntityResponseDto> userEntityResponseDtos = new ArrayList<>();
        users.forEach(userEntity -> {
            UserEntityResponseDto userEntityResponseDto = new UserEntityResponseDto(userEntity);
        });
        return userEntityResponseDtos;
    }

    public UserEntityResponseDto getUserById(UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("User not found");

        return new UserEntityResponseDto(user);
    }

    public UserEntity createUser(UserEntityRequestDto user) {
        UserEntity userEntity = UserEntity
                .builder()
                .id(UUID.randomUUID())

                // user information
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .passwordHash(user.getPassword())

                // default values
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .tasks(new ArrayList<>())
                .enabled(true)
                .build();

        return userEntityRepository.save(userEntity);
    }

    public UserEntity updateUser(UUID id, UserEntityRequestDto user) {
        UserEntity userEntity = userEntityRepository.findById(id).orElse(null);
        if (userEntity == null) throw new EntityNotFoundException("User not found");

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPasswordHash(user.getPassword());
        userEntity.setCreatedAt(LocalDate.now());
        userEntity.setUpdatedAt(LocalDate.now());

        return userEntityRepository.save(userEntity);
    }

    public String deleteUser(UUID id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return "User not found";
        }
        userEntityRepository.delete(userEntity);
        return "User deleted";
    }

}
