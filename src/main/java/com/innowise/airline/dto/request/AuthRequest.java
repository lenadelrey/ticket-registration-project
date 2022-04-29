package com.innowise.airline.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AuthRequest {
    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
