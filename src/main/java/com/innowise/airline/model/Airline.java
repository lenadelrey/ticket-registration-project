package com.innowise.airline.model;

import com.sun.xml.bind.v2.TODO;
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

    //TODO: Необходимость двусторонней связи?
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "from")
    private Set<Flight> flightsFrom;

    //TODO: Необходимость двусторонней связи?
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "to")
    private Set<Flight> flightsTo;
}
