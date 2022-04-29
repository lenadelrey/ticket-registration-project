package com.innowise.airline.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_airline_from_id", insertable = false, updatable = false)
    private Long fromId;

    @Column(name = "fk_airline_to_id", insertable = false, updatable = false)
    private Long toId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_airline_from_id", nullable = false)
    private Airline airlineFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_airline_to_id", nullable = false)
    private Airline airlineTo;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "count_of_tickets")
    private int countOfTickets;
}
