package com.innowise.airline.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "flight_id")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_flight_id", nullable = false)
    private Flight flight;

}
