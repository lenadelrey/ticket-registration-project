package com.innowise.airline.controller;

import com.innowise.airline.dto.request.TicketRequestDto;
import com.innowise.airline.dto.response.TicketResponseDto;
import com.innowise.airline.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{email}")
    public ResponseEntity<TicketResponseDto> create(@RequestBody @Valid TicketRequestDto ticketRequestDto, @PathVariable String email) {
        return new ResponseEntity<>(ticketService.create(ticketRequestDto, email), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAll() {
        return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> update(@RequestBody @Valid TicketRequestDto ticketRequestDto, @PathVariable Long id) {
        return new ResponseEntity<>(ticketService.updateById(ticketRequestDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.deleteById(id), HttpStatus.OK);
    }
}
