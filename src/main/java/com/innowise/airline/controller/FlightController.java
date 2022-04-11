package com.innowise.airline.controller;

import com.innowise.airline.dto.request.FlightRequest;
import com.innowise.airline.dto.response.FlightDto;
import com.innowise.airline.mapper.FlightMapper;
import com.innowise.airline.model.Flight;
import com.innowise.airline.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/flight")
public class FlightController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<FlightDto> create(@RequestBody @Valid FlightRequest flightRequest) {
        Flight flight = flightMapper.mapFlightRequestToFlight(flightRequest);
        return new ResponseEntity<>(flightMapper.mapFlightToFlightDto(flightService.create(flight)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<FlightDto>> getAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(flightMapper.mapPageFlightToPageFlightDto(flightService.getAll(pageable)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(flightMapper.mapFlightToFlightDto(flightService.getById(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<FlightDto> update(@Valid @RequestBody FlightRequest flightRequest, @PathVariable Long id) {
        Flight flight = flightMapper.mapFlightRequestToFlight(flightRequest);
        return new ResponseEntity<>(flightMapper.mapFlightToFlightDto(flightService.updateById(flight, id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
