package com.innowise.airline.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

//TODO: Для больширх сущностей рекомендую использовать Builder

@Entity
@Getter
@Setter
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
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

    //TODO: Необходимость двусторонней связи?
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private Set<Ticket> tickets;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "count_of_tickets")
    private int countOfTickets;

}
