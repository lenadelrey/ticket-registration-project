package com.innowise.airline.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

//TODO: Достаточно просто TicketRequest, сделать для каждой CRUD операции собственный request.

@Getter
@Setter
@Builder
public class TicketRequestDto {
    @NotBlank
    @Positive
    private Long flightId;

    @NotBlank
    @Positive
    private Long userId;

}
