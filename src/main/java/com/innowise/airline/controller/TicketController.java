package com.innowise.airline.controller;

import com.innowise.airline.dto.request.TicketRequest;
import com.innowise.airline.dto.response.TicketDto;
import com.innowise.airline.mapper.TicketMapper;
import com.innowise.airline.model.Ticket;
import com.innowise.airline.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping("/{id}")
    public ResponseEntity<TicketDto> createTicket(@RequestBody @Valid TicketRequest ticketRequest, @PathVariable Long id) {
        Ticket ticket = ticketMapper.mapTicketRequestToTicket(ticketRequest);
        return new ResponseEntity<>(ticketMapper.mapTicketToTicketDto(ticketService.create(ticket, id).orElseThrow()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(ticketMapper.mapPageTicketToPageTicketDto(ticketService.getAll(pageable)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ticketMapper.mapTicketToTicketDto(ticketService.getById(id).orElseThrow()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TicketDto> update(@RequestBody @Valid TicketRequest ticketRequest, @PathVariable Long id) {
        Ticket ticket = ticketMapper.mapTicketRequestToTicket(ticketRequest);
        return new ResponseEntity<>(ticketMapper.mapTicketToTicketDto(ticketService.updateById(ticket, id).orElseThrow()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
