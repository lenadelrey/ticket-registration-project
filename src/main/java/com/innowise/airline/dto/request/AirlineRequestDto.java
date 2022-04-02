package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class AirlineRequestDto {

    @NotBlank(message = "Необходимо указать название")
    private String name;

}
