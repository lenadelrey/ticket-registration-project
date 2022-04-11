package com.innowise.airline.service;

import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.model.Airline;
import com.innowise.airline.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    public Optional<Airline> create(Airline airline) {
        return Optional.of(airlineRepository.save(airline));
    }

    public Optional<Airline> getById(Long id) {
        return Optional.ofNullable(airlineRepository.findById(id).orElseThrow(IsNotExistException::new));
    }

    public Page<Airline> getAll(Pageable pageable) {
        return airlineRepository.findAll(pageable);
    }

    @Transactional
    public Optional<Airline> updateById(Airline airline, Long id) {
        if (!airlineRepository.existsById(id)) {
            throw new IsNotExistException();
        }

        airline.setId(id);
        return Optional.of(airlineRepository.save(airline));
    }

    @Transactional
    public void deleteById(Long id) {
        airlineRepository.findById(id).ifPresent(airlineRepository::delete);
    }
}
