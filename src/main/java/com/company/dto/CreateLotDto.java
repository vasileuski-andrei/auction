package com.company.dto;

import com.company.entity.LotStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateLotDto {

    String lotName;
    String owner;
    String startPrice;
    String saleTerm;
    LotStatus lotStatus;
}
