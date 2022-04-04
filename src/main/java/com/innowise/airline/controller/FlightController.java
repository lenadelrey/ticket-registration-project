package com.innowise.airline.controller;

import com.innowise.airline.dto.request.FlightRequestDto;
import com.innowise.airline.dto.response.FlightResponseDto;
import com.innowise.airline.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDto> create(@RequestBody @Valid FlightRequestDto flightRequestDto) {
        return new ResponseEntity<>(flightService.create(flightRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAll() {
        return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> update(@Valid @RequestBody FlightRequestDto flightRequestDto, @PathVariable Long id) {
        return new ResponseEntity<>(flightService.updateById(flightRequestDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.deleteById(id), HttpStatus.OK);
    }

}
