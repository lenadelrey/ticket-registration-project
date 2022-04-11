package com.innowise.airline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "airline")
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //TODO: Необходимость двусторонней связи?
    //да может она и не нужна, все равно все в бд хранится, при необходимости достать оттуда не составит особого труда
}
