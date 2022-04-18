package com.innowise.airline.repository;

import com.innowise.airline.model.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

//    TODO: зачем override? Зачем вообще явно указывать этот метод, он есть
//     в PagingAndSortingRepository, предке JpaRepository(актуально здесь и далее)
    @Override
    Page<Airline> findAll(Pageable pageable);
}
