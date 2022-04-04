package com.innowise.airline.controller;

import com.innowise.airline.dto.request.FlightRequestDto;
import com.innowise.airline.dto.response.FlightResponseDto;
import com.innowise.airline.mapper.FlightMapper;
import com.innowise.airline.model.Flight;
import com.innowise.airline.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDto> create(@RequestBody @Valid FlightRequestDto flightRequestDto) {
        Flight flight = FlightMapper.mapFlightRequestDtoToFlight(flightRequestDto);
        return new ResponseEntity<>(FlightMapper.mapFlightToFlightResponseDto(flightService.create(flight)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAll() {
        return new ResponseEntity<>(flightService.getAll()
                .stream()
                .map(FlightMapper::mapFlightToFlightResponseDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(FlightMapper.mapFlightToFlightResponseDto(flightService.getById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> update(@Valid @RequestBody FlightRequestDto flightRequestDto, @PathVariable Long id) {
        Flight flight = FlightMapper.mapFlightRequestDtoToFlight(flightRequestDto);
        return new ResponseEntity<>(FlightMapper.mapFlightToFlightResponseDto(flightService.updateById(flight, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.deleteById(id), HttpStatus.OK);
    }

}
