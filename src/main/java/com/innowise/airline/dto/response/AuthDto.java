package com.innowise.airline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthDto {
    private String accessToken;
    private String refreshToken;
}
