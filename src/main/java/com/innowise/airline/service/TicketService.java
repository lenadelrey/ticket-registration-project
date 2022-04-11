package com.innowise.airline.service;

import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.model.Flight;
import com.innowise.airline.model.Ticket;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.FlightRepository;
import com.innowise.airline.repository.TicketRepository;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @Transactional
    public Ticket create(Ticket ticket, Long id) {
        Flight flight = flightRepository.getById(ticket.getFlightId());

        if (flight.getCountOfTickets() == 0) {
            return null;
        }

        flight.setCountOfTickets(flight.getCountOfTickets() - 1);
        Flight saved = flightRepository.save(flight);
        ticket.setFlight(saved);

        User user = userRepository.getById(id);
        ticket.setUserId(user.getId());
        ticket.setUser(user);

        return ticketRepository.save(ticket);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id).orElseThrow(IsNotExistException::new);

    }

    public Page<Ticket> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Transactional
    public Ticket updateById(Ticket ticket, Long id) {
        ticketRepository.findById(id).orElseThrow(IsNotExistException::new);

        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteById(Long id) {
        ticketRepository.findById(id).ifPresent(ticketRepository::delete);
    }
}
