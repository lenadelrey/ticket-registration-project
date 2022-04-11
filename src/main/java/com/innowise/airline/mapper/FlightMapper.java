package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.FlightRequest;
import com.innowise.airline.dto.response.FlightDto;
import com.innowise.airline.model.Flight;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    Flight mapFlightRequestToFlight(FlightRequest flightRequest);

    FlightDto mapFlightToFlightDto(Flight flight);

    default Page<FlightDto> mapPageFlightToPageFlightDto(Page<Flight> flights) {
        return flights.map(this::mapFlightToFlightDto);
    }
}
