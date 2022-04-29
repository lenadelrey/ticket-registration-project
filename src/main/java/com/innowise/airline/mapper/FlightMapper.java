package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.FlightRequest;
import com.innowise.airline.dto.response.FlightDto;
import com.innowise.airline.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "flightDate",target = "flightDate", dateFormat = "dd-MM-yyyy hh:mm")
    Flight mapFlightRequestToFlight(FlightRequest flightRequest);

    @Mapping(source = "flightDate",target = "flightDate", dateFormat = "dd-MM-yyyy hh:mm")
    FlightDto mapFlightToFlightDto(Flight flight);

    default Page<FlightDto> mapPageFlightToPageFlightDto(Page<Flight> flights) {
        return flights.map(this::mapFlightToFlightDto);
    }

//    ZonedDateTime map(LocalDateTime value, ZoneId zoneId);
//    LocalDateTime map(ZonedDateTime value);

//    @Named("flightDate")
//    static LocalDateTime map(ZonedDateTime date) {
//        if (date == null) {
//            return null;
//        }
//
//        return LocalDateTime.ofInstant(Instant.from(date), date.getZone());
//    }
//    @Named("flightDate")
//    static ZonedDateTime map(LocalDateTime date) {
//        if (date == null) {
//            return null;
//        }
//
//        return ZonedDateTime.of(date, ZoneId.systemDefault());
//    }
}
