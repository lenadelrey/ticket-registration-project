package com.innowise.airline.service;

import com.innowise.airline.model.Airline;
import com.innowise.airline.model.Flight;
import com.innowise.airline.repository.AirlineRepository;
import com.innowise.airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Transactional
    @Override
    public Flight create(Flight flight) {
        Airline from = airlineRepository.getById(flight.getFromId());
        Airline to = airlineRepository.getById(flight.getToId());
        flight.setFrom(from);
        flight.setTo(to);
        return flightRepository.save(flight);
    }

    @Override
    public Flight getById(Long id) {
        if (flightRepository.existsById(id)) {
            return flightRepository.getById(id);
        }
        return null;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Transactional
    @Override
    public Flight updateById(Flight flight, Long id) {
        if (flightRepository.existsById(id)) {
            flight.setId(id);
            return flightRepository.save(flight);
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
