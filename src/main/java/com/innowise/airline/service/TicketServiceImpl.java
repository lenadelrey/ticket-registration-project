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
    public Ticket create(Ticket ticket, String email) {
        Flight flight = flightRepository.getById(ticket.getFlightId());

        if (flight.getCountOfTickets() == 0) {
            return null;
        }

        flight.setCountOfTickets(flight.getCountOfTickets() - 1);
        Flight saved = flightRepository.save(flight);
        ticket.setFlight(saved);
        //TODO: работать с id сущности, поиск по email использовать только для авторизации, аутентификации, валидации при регистрации
        User user = userRepository.findByEmail(email);
        ticket.setUserId(user.getId());
        ticket.setUser(user);
        //TODO: разделять логические блоки кода, в данном случае содержимое create-метода плохо читаемо и трудно для восприятия
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        if (!ticketRepository.existsById(id)) {
            //TODO: литералы вынести в константы, либо инкапулировать сообщение в конструктор
            throw new IsNotExistException("no such ticket", "getById");
        }
        return ticketRepository.getById(id);
    }

    @Override
    //TODO: Использовать пагинацию
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Transactional
    @Override
    public Ticket updateById(Ticket ticket, Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IsNotExistException("no such ticket", "update");
        }
        //TODO: пустая строка между логическими блоками
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        //TODO: использовать методы Optional
        if (!ticketRepository.existsById(id)) {
            throw new IsNotExistException("no such ticket", "delete");
        }
        //TODO: разве return ticketRepository.deleteById() не эквивалетно написанным ниже двум строкам? Заменять одной в таких ситуациях.
        //TODO: опять же, мотивация использования boolean?
        ticketRepository.deleteById(id);
        return true;
    }
}
