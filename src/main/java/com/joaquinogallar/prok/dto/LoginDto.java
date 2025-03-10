package com.joaquinogallar.prok.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String token;

    private Long expiresIn;
}
