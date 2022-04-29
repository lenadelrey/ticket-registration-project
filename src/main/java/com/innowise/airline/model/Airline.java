package com.innowise.airline.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
//TODO: нет смысла указывать таблицу, если она совпадает с lowercase'ом названия класса. Условно:
// Airline->airline - указывать @Table не надо, User->"user"; AirDefense-> air_defense - надо. Хотя последнее хиб, мб, сам сможет сматчить
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
