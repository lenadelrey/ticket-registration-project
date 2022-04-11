package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.AirlineRequest;
import com.innowise.airline.dto.response.AirlineDto;
import com.innowise.airline.model.Airline;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    Airline mapAirlineRequestToAirline(AirlineRequest airlineRequest);

    AirlineDto mapAirlineToAirlineDto(Airline airline);

    default Page<AirlineDto> mapPageAirlineToPageAirlineDto(Page<Airline> airlines) {
        return airlines.map(this::mapAirlineToAirlineDto);
    }

}
