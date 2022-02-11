package com.company.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LotDto {

    private Integer id;
    private String name;

}
