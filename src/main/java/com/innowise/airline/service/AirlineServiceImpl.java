package com.innowise.airline.service;

import com.innowise.airline.model.Airline;
import com.innowise.airline.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    @Override
    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline getById(Long id) {
        if (airlineRepository.existsById(id)) {
            return airlineRepository.getById(id);
        }
        return null;
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    @Transactional
    @Override
    public Airline updateById(Airline airline, Long id) {
        if (airlineRepository.existsById(id)) {
            airline.setId(id);
            return airlineRepository.save(airline);
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (airlineRepository.existsById(id)) {
            airlineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
