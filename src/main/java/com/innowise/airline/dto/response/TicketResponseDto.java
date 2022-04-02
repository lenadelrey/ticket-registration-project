package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketResponseDto {

    private UserResponseDto userResponseDto;

    private FlightResponseDto flightResponseDto;

}
