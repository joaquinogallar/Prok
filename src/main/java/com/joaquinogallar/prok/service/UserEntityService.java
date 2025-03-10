package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.dto.UserEntityResponseDto;
import com.joaquinogallar.prok.entity.Role;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntityResponseDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserEntityResponseDto> userEntityResponseDtos = new ArrayList<>();
        users.forEach(userEntity -> {
            UserEntityResponseDto userEntityResponseDto = new UserEntityResponseDto(userEntity);
            userEntityResponseDtos.add(userEntityResponseDto);
        });
        return userEntityResponseDtos;
    }

    public UserEntityResponseDto getUserById(UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException("User not found");

        return new UserEntityResponseDto(user);
    }

    /*
    public void updateUser(UUID id, UserEntityRequestDto user) {
        String[] trimmedNames = trimName(user.getFullName());

        UserEntity userEntity = userEntityRepository.findById(id).orElse(null);
        if (userEntity == null) throw new EntityNotFoundException("User not found");

        userEntity.setFirstName(trimmedNames[0]);
        userEntity.setLastName(trimmedNames[1]);
        userEntity.setEmail(user.getEmail());
        userEntity.setUpdatedAt(LocalDate.now());

        userEntityRepository.save(userEntity);
    }
     */

    public String deleteUser(UUID id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return "User not found";
        }
        userEntityRepository.delete(userEntity);
        return "User deleted";
    }

}
