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

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Transactional
    public Flight create(Flight flight) {
        Airline from = airlineRepository.getById(flight.getFromId());
        Airline to = airlineRepository.getById(flight.getToId());

        flight.setFrom(from);
        flight.setTo(to);
        return flightRepository.save(flight);
    }

    public Flight getById(Long id) {
        return flightRepository.findById(id).orElseThrow(IsNotExistException::new);
    }

    public Page<Flight> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Transactional
    public Flight updateById(Flight flight, Long id) {
        flightRepository.findById(id).orElseThrow(IsNotExistException::new);

        flight.setId(id);
        return flightRepository.save(flight);
    }

    @Transactional
    public void deleteById(Long id) {
        flightRepository.findById(id).ifPresent(flightRepository::delete);
    }
}
