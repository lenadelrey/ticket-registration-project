package com.innowise.airline.controller;

import com.innowise.airline.dto.request.AirlineRequestDto;
import com.innowise.airline.dto.response.AirlineResponseDto;
import com.innowise.airline.mapper.AirlineMapper;
import com.innowise.airline.model.Airline;
import com.innowise.airline.service.AirlineService;
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
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponseDto> create(@Valid @RequestBody AirlineRequestDto airlineRequestDto) {
        Airline airline = AirlineMapper.mapAirlineRequestDtoToAirline(airlineRequestDto);
        return new ResponseEntity<>(AirlineMapper.mapAirlineToAirlineResponseDto(airlineService.create(airline)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AirlineResponseDto>> getAll() {
        return new ResponseEntity<>(airlineService.getAll()
                .stream()
                .map(AirlineMapper::mapAirlineToAirlineResponseDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(AirlineMapper.mapAirlineToAirlineResponseDto(airlineService.getById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponseDto> update(@Valid @RequestBody AirlineRequestDto airlineRequestDto, @PathVariable Long id) {
        Airline airline = AirlineMapper.mapAirlineRequestDtoToAirline(airlineRequestDto);
        return new ResponseEntity<>(AirlineMapper.mapAirlineToAirlineResponseDto(airlineService.updateById(airline, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(airlineService.deleteById(id), HttpStatus.OK);
    }

}
