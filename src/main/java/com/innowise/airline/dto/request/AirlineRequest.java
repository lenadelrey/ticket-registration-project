package com.innowise.airline.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AirlineRequest {
//TODO: зачем здесь что-то, кроме геттеров? конструктор без параметров сущетсвует по умолчанию, другим в реквесте взяться неоткуда
    @NotBlank(message = "Необходимо указать название")
    private String name;

}
