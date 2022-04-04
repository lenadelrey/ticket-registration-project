package com.innowise.airline.service;

import com.innowise.airline.dto.request.AirlineRequestDto;
import com.innowise.airline.dto.response.AirlineResponseDto;
import com.innowise.airline.model.Airline;

import java.util.List;

public interface AirlineService {

    AirlineResponseDto create(AirlineRequestDto airlineRequestDto);

    AirlineResponseDto getById(Long id);

    List<AirlineResponseDto> getAll();

    AirlineResponseDto updateById(AirlineRequestDto airlineRequestDto, Long id);

    boolean deleteById(Long id);

}
