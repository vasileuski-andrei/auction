package com.company.dto;

import com.company.entity.LotStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLotDto {

    private String lotName;
    private String owner;
    private String startBid;
    private String saleTerm;
    private LotStatus lotStatus;
}
