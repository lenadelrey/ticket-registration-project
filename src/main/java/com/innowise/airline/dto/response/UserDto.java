package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}
