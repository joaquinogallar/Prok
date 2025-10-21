package com.joaquinogallar.prok.user.dto;

import com.joaquinogallar.prok.user.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

public record UserEntityDto(UUID id, String firstName, String lastName, String email, LocalDate createdAt) {}
