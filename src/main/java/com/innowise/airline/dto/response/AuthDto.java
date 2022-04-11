package com.innowise.airline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthDto {

    private final String type = "Bearer ";
    private String accessToken;
    private String refreshToken;

}
