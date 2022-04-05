package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//TODO: Достаточно просто UserRequest, сделать для каждой CRUD операции собственный request.

@Getter
@Setter
@Builder
public class UserRequestDto {

    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @Past(message = "Дата рождения не должна быть больше текущей")
    private LocalDate dateOfBirth;
}
