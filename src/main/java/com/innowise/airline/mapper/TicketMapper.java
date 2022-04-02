package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.model.Ticket;

public class TicketMapper {

    public static Ticket mapTicketRequestDtoToTicket(TicketRequestDto ticketRequestDto) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(ticketRequestDto.getFlightId());
        ticket.setUserId(ticketRequestDto.getUserId());
        return ticket;
    }

    public static TicketResponseDto mapTicketToTicketResponseDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .userResponseDto(UserMapper.mapUserToUserResponseDto(ticket.getUser()))
                .flightResponseDto(FlightMapper.mapFlightToFlightResponseDto(ticket.getFlight()))
                .build();
    }

}
