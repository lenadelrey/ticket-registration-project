package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightDto {
    private Long fromId;
    private Long toId;
    //private ZonedDateTime flightDate;
    private LocalDateTime flightDate;
    private int countOfTickets;
}
