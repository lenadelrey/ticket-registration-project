package com.innowise.airline.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//TODO: Достаточно просто UserRequest, сделать для каждой CRUD операции собственный request.
// не вижу смысла делать для каждой CRUD операции собственный request, поскольку единственные два метода, на которые мне приходит request
// это create и update, и оба эти метода, по моему мнению, могут принимать одинаковые поля, соответственно делать две одинаковые сущности
// с разными именами не имеет смысла, это касается всех моих entity

@Getter
public class UserRequest {
    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16, message = "password must be between 8 and 16 size")
    private String password;

    @Past(message = "Дата рождения не должна быть больше текущей")
    private LocalDate dateOfBirth;
}
