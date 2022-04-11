package com.innowise.airline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromId;
    private Long toId;

    @ManyToOne
    @JoinColumn(name = "fk_airline_from_id", nullable = false)
    private Airline from;

    @ManyToOne
    @JoinColumn(name = "fk_airline_to_id", nullable = false)
    private Airline to;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "count_of_tickets")
    private int countOfTickets;

}
