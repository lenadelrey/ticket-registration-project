package com.innowise.airline.service;

import com.innowise.airline.exception.ObjectNotExistException;
import com.innowise.airline.model.Airline;
import com.innowise.airline.repository.AirlineRepository;
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
public class AirlineService {
    private final AirlineRepository airlineRepository;

    @Cacheable("airlines")
    @Transactional
    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Cacheable("airlines")
    public Airline getById(Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(ObjectNotExistException::new);
    }

    @Cacheable("airlines")
    public Page<Airline> getAll(Pageable pageable) {
        return airlineRepository.findAll(pageable);
    }

    @CachePut("airlines")
    @Transactional
    public Airline update(Airline airline, Long id) {
        airlineRepository.findById(id)
                .orElseThrow(ObjectNotExistException::new);

        airline.setId(id);

        return airlineRepository.save(airline);
    }

    @CacheEvict("airlines")
    @Transactional
    public void deleteById(Long id) {
        airlineRepository.findById(id)
                .ifPresent(airlineRepository::delete);
    }
}
