package com.innowise.airline.service;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.mapper.TicketMapper;
import com.innowise.airline.model.Flight;
import com.innowise.airline.model.Ticket;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.FlightRepository;
import com.innowise.airline.repository.TicketRepository;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public TicketResponseDto create(TicketRequestDto ticketRequestDto, String email) {
        Ticket ticket = TicketMapper.mapTicketRequestDtoToTicket(ticketRequestDto);
        Flight flight = flightRepository.getById(ticket.getFlightId());

        if (flight.getCountOfTickets() == 0) {
            return null;
        }

        flight.setCountOfTickets(flight.getCountOfTickets() - 1);
        Flight saved = flightRepository.save(flight);
        ticket.setFlight(saved);
        User user = userRepository.findByEmail(email);
        ticket.setUserId(user.getId());
        ticket.setUser(user);
        return TicketMapper.mapTicketToTicketResponseDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponseDto getById(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IsNotExistException("no such ticket", "getById");
        }
        return TicketMapper.mapTicketToTicketResponseDto(ticketRepository.getById(id));
    }

    @Override
    public List<TicketResponseDto> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(TicketMapper::mapTicketToTicketResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TicketResponseDto updateById(TicketRequestDto ticketRequestDto, Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IsNotExistException("no such ticket", "update");
        }
        Ticket ticket = TicketMapper.mapTicketRequestDtoToTicket(ticketRequestDto);

        ticket.setId(id);
        return TicketMapper.mapTicketToTicketResponseDto(ticketRepository.save(ticket));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IsNotExistException("no such ticket", "delete");
        }
        ticketRepository.deleteById(id);
        return true;
    }
}
