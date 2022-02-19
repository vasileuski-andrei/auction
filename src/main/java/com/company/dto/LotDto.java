package com.company.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LotDto {

    Integer id;
    String lotName;
    String owner;
    String lotStatus;
    String startPrice;
    String lastBet;
    Integer time;

}
