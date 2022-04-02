package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.AirlineRequestDto;
import com.innowise.airline.dto.response.AirlineResponseDto;
import com.innowise.airline.model.Airline;

public class AirlineMapper {

    public static Airline mapAirlineRequestDtoToAirline(AirlineRequestDto airlineRequestDto) {
        Airline airline = new Airline();
        airline.setName(airlineRequestDto.getName());
        return airline;
    }

    public static AirlineResponseDto mapAirlineToAirlineResponseDto(Airline airline) {
        return AirlineResponseDto.builder()
                .name(airline.getName())
                .build();
    }

}
