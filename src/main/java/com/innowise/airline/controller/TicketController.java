package com.innowise.airline.controller;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.mapper.TicketMapper;
import com.innowise.airline.model.Ticket;
import com.innowise.airline.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{email}")
    public ResponseEntity<TicketResponseDto> create(@RequestBody @Valid TicketRequestDto ticketRequestDto, @PathVariable String email) {
        Ticket ticket = TicketMapper.mapTicketRequestDtoToTicket(ticketRequestDto);
        return new ResponseEntity<>(TicketMapper.mapTicketToTicketResponseDto(ticketService.create(ticket, email)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAll() {
        return new ResponseEntity<>(ticketService.getAll()
                .stream()
                .map(TicketMapper::mapTicketToTicketResponseDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(TicketMapper.mapTicketToTicketResponseDto(ticketService.getById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> update(@RequestBody @Valid TicketRequestDto ticketRequestDto, @PathVariable Long id) {
        Ticket ticket = TicketMapper.mapTicketRequestDtoToTicket(ticketRequestDto);
        return new ResponseEntity<>(TicketMapper.mapTicketToTicketResponseDto(ticketService.updateById(ticket, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.deleteById(id), HttpStatus.OK);
    }
}
