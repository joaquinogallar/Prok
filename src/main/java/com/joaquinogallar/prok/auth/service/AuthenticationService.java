package com.joaquinogallar.prok.auth.service;

import com.joaquinogallar.prok.user.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.user.dto.UserEntityDto;
import com.joaquinogallar.prok.user.dto.UserLoginDto;
import com.joaquinogallar.prok.user.entity.Role;
import com.joaquinogallar.prok.user.entity.UserEntity;
import com.joaquinogallar.prok.user.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String[] trimName(String fullName) {
        String[] names = fullName.split(" ", 2);
        String[] trimmedNames = new String[2];

        trimmedNames[0] = names[0];
        trimmedNames[1] = names.length > 1 ? names[1] : "";

        return trimmedNames;
    }

    public UserEntityDto signUp(UserEntityRequestDto user) {
        String[] trimmedNames = trimName(user.getFullName());
        UserEntity userEntity = UserEntity
                .builder()
                .firstName(trimmedNames[0])
                .lastName(trimmedNames[1])
                .role(Role.USER)
                .email(user.getEmail())
                .passwordHash(passwordEncoder.encode(user.getPassword()))
                .build();

        userEntityRepository.save(userEntity);

        return new UserEntityDto(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail() ,userEntity.getCreatedAt());
    }

    public UserEntity authenticate(UserLoginDto userData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userData.email(),
                        userData.password()
                )
        );

        return userEntityRepository.findByEmail(userData.email()).orElseThrow();
    }


}
