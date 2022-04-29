package com.innowise.airline.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TicketRequest {
    @NotBlank
    private Long flightId;

    @NotBlank
    private Long userId;
}
