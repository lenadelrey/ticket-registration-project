package com.innowise.airline.service;

import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.model.Airline;
import com.innowise.airline.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline getById(Long id) {
        return airlineRepository.findById(id).orElseThrow(IsNotExistException::new);
    }

    public Page<Airline> getAll(Pageable pageable) {
        return airlineRepository.findAll(pageable);
    }

    @Transactional
//    TODO: почему именно updateById, а не просто update?
    public Airline updateById(Airline airline, Long id) {
        airlineRepository.findById(id).orElseThrow(IsNotExistException::new);

        airline.setId(id);
        return airlineRepository.save(airline);
    }

    @Transactional
    public void deleteById(Long id) {
        airlineRepository.findById(id).ifPresent(airlineRepository::delete);
    }
}
