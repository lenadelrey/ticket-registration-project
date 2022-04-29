package com.innowise.airline.controller;

import com.innowise.airline.dto.request.AirlineRequest;
import com.innowise.airline.dto.response.AirlineDto;
import com.innowise.airline.mapper.AirlineMapper;
import com.innowise.airline.model.Airline;
import com.innowise.airline.service.AirlineService;
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
@RequestMapping("/user/airline")
public class AirlineController {
    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AirlineDto> create(@Valid @RequestBody AirlineRequest airlineRequest) {
        Airline airline = airlineMapper.mapAirlineRequestToAirline(airlineRequest);

        return new ResponseEntity<>(airlineMapper.mapAirlineToAirlineDto(airlineService.create(airline)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AirlineDto>> getAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(airlineMapper.mapPageAirlineToPageAirlineDto(airlineService.getAll(pageable)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(airlineMapper.mapAirlineToAirlineDto(airlineService.getById(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<AirlineDto> update(@Valid @RequestBody AirlineRequest airlineRequest, @PathVariable Long id) {
        Airline airline = airlineMapper.mapAirlineRequestToAirline(airlineRequest);

        return new ResponseEntity<>(airlineMapper.mapAirlineToAirlineDto(airlineService.update(airline, id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        airlineService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
