package com.innowise.airline.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
//TODO: про множественные названия уже говорил
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {//TODO: лишние пустые строки перед первым полем и после последнего. Между полями - всегда пустая строка, если класс - энтити

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Builder.Default
    private boolean deleted = false;

}


