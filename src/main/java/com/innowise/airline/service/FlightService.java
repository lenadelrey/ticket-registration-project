package com.innowise.airline.service;

import com.innowise.airline.model.Flight;

import java.util.List;

public interface FlightService {

    Flight create(Flight flight);

    Flight getById(Long id);

    List<Flight> getAll();

    Flight updateById(Flight flight, Long id);

    boolean deleteById(Long id);

}
