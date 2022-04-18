package com.innowise.airline.dto.response;

import lombok.Data;

@Data
//TODO: лишние пустые строки
public class TicketDto {

    private UserDto userDto;
    private FlightDto flightResponseDto;

}
