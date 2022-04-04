package com.innowise.airline.controller;

import com.innowise.airline.dto.request.AirlineRequestDto;
import com.innowise.airline.dto.response.AirlineResponseDto;
import com.innowise.airline.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponseDto> create(@Valid @RequestBody AirlineRequestDto airlineRequestDto) {
        return new ResponseEntity<>(airlineService.create(airlineRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AirlineResponseDto>> getAll() {
        return new ResponseEntity<>(airlineService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(airlineService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponseDto> update(@Valid @RequestBody AirlineRequestDto airlineRequestDto, @PathVariable Long id) {
        return new ResponseEntity<>(airlineService.updateById(airlineRequestDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(airlineService.deleteById(id), HttpStatus.OK);
    }

}
