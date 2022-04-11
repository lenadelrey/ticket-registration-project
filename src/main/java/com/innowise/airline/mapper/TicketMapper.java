package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.TicketRequest;
import com.innowise.airline.dto.response.TicketDto;
import com.innowise.airline.model.Ticket;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket mapTicketRequestToTicket(TicketRequest ticketRequest);

    TicketDto mapTicketToTicketDto(Ticket ticket);

    default Page<TicketDto> mapPageTicketToPageTicketDto(Page<Ticket> tickets) {
        return tickets.map(this::mapTicketToTicketDto);
    }

}
