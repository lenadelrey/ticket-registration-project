package com.innowise.airline.service;

import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.model.Airline;
import com.innowise.airline.model.Flight;
import com.innowise.airline.repository.AirlineRepository;
import com.innowise.airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Transactional
    public Optional<Flight> create(Flight flight) {
        Airline from = airlineRepository.getById(flight.getFromId());
        Airline to = airlineRepository.getById(flight.getToId());
        flight.setFrom(from);
        flight.setTo(to);
        return Optional.of(flightRepository.save(flight));
    }

    public Optional<Flight> getById(Long id) {
        return Optional.ofNullable(flightRepository.findById(id).orElseThrow(IsNotExistException::new));
    }

    public Page<Flight> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Transactional
    public Optional<Flight> updateById(Flight flight, Long id) {
        if (!flightRepository.existsById(id)) {
            throw new IsNotExistException("update");
        }

        flight.setId(id);
        return Optional.of(flightRepository.save(flight));
    }

    @Transactional
    public void deleteById(Long id) {
        flightRepository.findById(id).ifPresent(flightRepository::delete);
    }
}
