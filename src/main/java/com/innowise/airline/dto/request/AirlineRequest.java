package com.innowise.airline.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AirlineRequest {

    @NotBlank(message = "Необходимо указать название")
    private String name;

}
