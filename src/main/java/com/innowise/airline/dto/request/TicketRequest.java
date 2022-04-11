package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
public class TicketRequest {
    @NotBlank
    @Positive
    private Long flightId;

    @NotBlank
    @Positive
    private Long userId;

}
