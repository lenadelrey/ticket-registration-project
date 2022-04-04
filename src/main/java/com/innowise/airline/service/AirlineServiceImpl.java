package com.innowise.airline.service;

import com.innowise.airline.dto.request.AirlineRequestDto;
import com.innowise.airline.dto.response.AirlineResponseDto;
import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.mapper.AirlineMapper;
import com.innowise.airline.model.Airline;
import com.innowise.airline.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    @Override
    public AirlineResponseDto create(AirlineRequestDto airlineRequestDto) {
        Airline airline = AirlineMapper.mapAirlineRequestDtoToAirline(airlineRequestDto);
        return AirlineMapper.mapAirlineToAirlineResponseDto(airlineRepository.save(airline));
    }

    @Override
    public AirlineResponseDto getById(Long id) {
        if (!airlineRepository.existsById(id)) {
            throw new IsNotExistException("no such airline", "getById");
        }
        return AirlineMapper.mapAirlineToAirlineResponseDto(airlineRepository.getById(id));
    }

    @Override
    public List<AirlineResponseDto> getAll() {
        return airlineRepository.findAll()
                .stream()
                .map(AirlineMapper::mapAirlineToAirlineResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AirlineResponseDto updateById(AirlineRequestDto airlineRequestDto, Long id) {
        if (!airlineRepository.existsById(id)) {
            throw new IsNotExistException("no such airline", "update");
        }
        Airline airline = AirlineMapper.mapAirlineRequestDtoToAirline(airlineRequestDto);
        airline.setId(id);
        return AirlineMapper.mapAirlineToAirlineResponseDto(airlineRepository.save(airline));
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
