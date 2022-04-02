package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightResponseDto {

    private AirlineResponseDto from;

    private AirlineResponseDto to;

    private LocalDateTime flightDate;

    private int countOfTickets;

}
