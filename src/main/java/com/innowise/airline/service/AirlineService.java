package com.innowise.airline.service;

import com.innowise.airline.model.Airline;

import java.util.List;

public interface AirlineService {

    Airline create(Airline airline);

    Airline getById(Long id);

    List<Airline> getAll();

    Airline updateById(Airline airline, Long id);

    //TODO: почему boolean?
    boolean deleteById(Long id);

}
