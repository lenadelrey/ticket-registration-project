package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.FlightRequestDto;
import com.innowise.airline.dto.response.FlightResponseDto;
import com.innowise.airline.model.Flight;
//TODO: Зачем создавать Builder, а потом его не использовать?)

public class FlightMapper {

    public static Flight mapFlightRequestDtoToFlight(FlightRequestDto flightRequestDto) {
        Flight flight = new Flight();
        flight.setFromId(flightRequestDto.getFromId());
        flight.setToId(flightRequestDto.getToId());
        flight.setFlightDate(flightRequestDto.getFlightDate());
        flight.setCountOfTickets(flightRequestDto.getCountOfTickets());
        return flight;
    }

    public static FlightResponseDto mapFlightToFlightResponseDto(Flight flight) {
        return FlightResponseDto.builder()
                .flightDate(flight.getFlightDate())
                .countOfTickets(flight.getCountOfTickets())
                .from(AirlineMapper.mapAirlineToAirlineResponseDto(flight.getFrom()))
                .to(AirlineMapper.mapAirlineToAirlineResponseDto(flight.getTo()))
                .build();
    }

}
