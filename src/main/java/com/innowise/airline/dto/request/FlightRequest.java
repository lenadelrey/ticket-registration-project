package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FlightRequest {

    @NotBlank
    @Positive
    private Long fromId;

    @NotBlank
    @Positive
    private Long toId;

    @FutureOrPresent(message = "Flight date could be in the future")
    private LocalDateTime flightDate;

    @NotBlank
    @Positive
    private int countOfTickets;
}