package com.innowise.airline.dto.request;

import lombok.Getter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class FlightRequest {
    @NotBlank
    private Long fromId;

    @NotBlank
    private Long toId;

    @FutureOrPresent(message = "Flight date could be in the future")
    //@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime flightDate;
    //private ZonedDateTime flightDate;

    @NotBlank
    @Positive
    private int countOfTickets;
}
