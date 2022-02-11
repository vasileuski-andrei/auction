package com.company.dto;

import com.company.entity.LotStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LotDto {

    private Integer id;
    private String name;
    private String owner;
    private String lotStatus;

}
