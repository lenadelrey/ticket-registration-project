package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//TODO: В данном случае исользование Builder избыточно. В остальных Dto можешь оставить
public class TicketResponseDto {

    private UserResponseDto userResponseDto;

    private FlightResponseDto flightResponseDto;

}
