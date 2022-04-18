package com.innowise.airline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//TODO: лишние пустые строки
@AllArgsConstructor
public class AuthDto {

    private final String type = "Bearer ";//TODO: для чего это поле?
    private String accessToken;
    private String refreshToken;

}
