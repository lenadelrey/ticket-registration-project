package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
//TODO: лишние пустые строки
//TODO: зачем всегда отдавать хеш пароля? это небезопасно. Почему не используется id в дто?
public class UserDto {

    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

}
