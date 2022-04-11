package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class AuthRequest {

    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

}
