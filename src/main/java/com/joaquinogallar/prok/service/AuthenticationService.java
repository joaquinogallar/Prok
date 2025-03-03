package com.joaquinogallar.prok.service;

import com.joaquinogallar.prok.dto.UserEntityRequestDto;
import com.joaquinogallar.prok.entity.Role;
import com.joaquinogallar.prok.entity.UserEntity;
import com.joaquinogallar.prok.repository.UserEntityRepository;
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
    private final UserEntityService userEntityService;

    @Autowired
    public AuthenticationService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserEntityService userEntityService) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userEntityService = userEntityService;
    }

    public UserEntity signUp(UserEntityRequestDto user) {
        String[] trimmedNames = userEntityService.trimName(user.getFullName());
        UserEntity userEntity = UserEntity
                .builder()
                .firstName(trimmedNames[0])
                .lastName(trimmedNames[1])
                .role(Role.USER)
                .email(user.getEmail())
                .passwordHash(passwordEncoder.encode(user.getPassword()))
                .build();

        userEntityRepository.save(userEntity);

        return userEntity;
    }



}
