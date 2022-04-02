package com.innowise.airline.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "airline")
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "from")
    private Set<Flight> flightsFrom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "to")
    private Set<Flight> flightsTo;
}
