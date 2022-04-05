package com.innowise.airline.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//TODO: Достаточно просто AirlineDto. Тоже самое в других Dto.
//TODO: В данном случае исользование Builder избыточно. В остальных Dto можешь оставить
public class AirlineResponseDto {

    private String name;

}
