package com.innowise.airline.service;

import com.innowise.airline.dto.request.FlightRequestDto;
import com.innowise.airline.dto.response.FlightResponseDto;
import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.mapper.FlightMapper;
import com.innowise.airline.model.Airline;
import com.innowise.airline.model.Flight;
import com.innowise.airline.repository.AirlineRepository;
import com.innowise.airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Transactional
    @Override
    public FlightResponseDto create(FlightRequestDto flightRequestDto) {
        Flight flight = FlightMapper.mapFlightRequestDtoToFlight(flightRequestDto);
        Airline from = airlineRepository.getById(flight.getFromId());
        Airline to = airlineRepository.getById(flight.getToId());
        flight.setFrom(from);
        flight.setTo(to);
        return FlightMapper.mapFlightToFlightResponseDto(flightRepository.save(flight));
    }

    @Override
    public FlightResponseDto getById(Long id) {
        if (flightRepository.existsById(id)) {
            return FlightMapper.mapFlightToFlightResponseDto(flightRepository.getById(id));
        }
        return null;
    }

    @Override
    public List<FlightResponseDto> getAll() {
        return flightRepository.findAll()
                .stream()
                .map(FlightMapper::mapFlightToFlightResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public FlightResponseDto updateById(FlightRequestDto flightRequestDto, Long id) {
        if (!flightRepository.existsById(id)) {
            throw new IsNotExistException("no such flight", "update");
        }
        Flight flight = FlightMapper.mapFlightRequestDtoToFlight(flightRequestDto);
        flight.setId(id);
        return FlightMapper.mapFlightToFlightResponseDto(flightRepository.save(flight));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
