package com.innowise.airline.dto.response;

import lombok.Data;

@Data
public class TicketDto {

    private UserDto userDto;
    private FlightDto flightResponseDto;

}
