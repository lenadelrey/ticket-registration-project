package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserDto {

    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

}
