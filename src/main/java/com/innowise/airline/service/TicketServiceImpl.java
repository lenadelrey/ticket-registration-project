package com.innowise.airline.service;

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

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Ticket create(Ticket ticket, String email) {
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
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        if (ticketRepository.existsById(id)) {
            return ticketRepository.getById(id);
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Transactional
    @Override
    public Ticket updateById(Ticket ticket, Long id) {
        if (ticketRepository.existsById(id)) {
            ticket.setId(id);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
