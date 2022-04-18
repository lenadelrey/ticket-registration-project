package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
//TODO: лишние пустые строки
public class FlightDto {

    private Long fromId;
    private Long toId;
    private ZonedDateTime flightDate;
    private int countOfTickets;

}
