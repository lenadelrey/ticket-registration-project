package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightDto {

    private AirlineDto from;
    private AirlineDto to;
    private LocalDateTime flightDate;
    private int countOfTickets;

}
