package com.innowise.airline.service;

import com.innowise.airline.exception.ObjectNotExistException;
import com.innowise.airline.model.Airline;
import com.innowise.airline.model.Flight;
import com.innowise.airline.repository.AirlineRepository;
import com.innowise.airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Cacheable("flights")
    @Transactional
    public Flight create(Flight flight) {
        Airline from = airlineRepository.getById(flight.getFromId());
        Airline to = airlineRepository.getById(flight.getToId());

        flight.setAirlineFrom(from);
        flight.setAirlineTo(to);

        return flightRepository.save(flight);
    }

    @Cacheable("flights")
    public Flight getById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(ObjectNotExistException::new);
    }

    @Cacheable("flights")
    public Page<Flight> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @CachePut("flights")
    @Transactional
    public Flight update(Flight flight, Long id) {
        flightRepository.findById(id)
                .orElseThrow(ObjectNotExistException::new);

        flight.setId(id);

        return flightRepository.save(flight);
    }

    @CacheEvict("flights")
    @Transactional
    public void deleteById(Long id) {
        flightRepository.findById(id)
                .ifPresent(flightRepository::delete);
    }
}
