package com.innowise.airline.service;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.model.Ticket;

import java.util.List;

public interface TicketService {

    TicketResponseDto create(TicketRequestDto ticketRequestDto, String email);

    TicketResponseDto getById(Long id);

    List<TicketResponseDto> getAll();

    TicketResponseDto updateById(TicketRequestDto ticketRequestDto, Long id);

    boolean deleteById(Long id);

}
