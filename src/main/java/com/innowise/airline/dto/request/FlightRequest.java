package com.innowise.airline.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequest {

    @NotBlank
    @Positive
    private Long fromId;

    @NotBlank
    @Positive
    private Long toId;

//    TODO: никаких LocalDateTime в дто и реквестах
    @FutureOrPresent(message = "Flight date could be in the future")
    //@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime flightDate;

    @NotBlank
    @Positive
    private int countOfTickets;
}
