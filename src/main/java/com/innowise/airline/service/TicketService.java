package com.innowise.airline.service;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket, String email);

    Ticket getById(Long id);

    List<Ticket> getAll();

    Ticket updateById(Ticket ticket, Long id);

    boolean deleteById(Long id);

}
