package com.innowise.airline.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AirlineRequest {
    @NotBlank(message = "Необходимо указать название")
    private String name;
}
