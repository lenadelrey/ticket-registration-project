package com.innowise.airline.service;

import com.innowise.airline.dto.request.FlightRequestDto;
import com.innowise.airline.dto.response.FlightResponseDto;
import com.innowise.airline.model.Flight;

import java.util.List;

public interface FlightService {

    FlightResponseDto create(FlightRequestDto flightRequestDto);

    FlightResponseDto getById(Long id);

    List<FlightResponseDto> getAll();

    FlightResponseDto updateById(FlightRequestDto flightRequestDto, Long id);

    boolean deleteById(Long id);

}
