package com.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LotDto {

    private Integer id;
    private String lotName;
    private String owner;
    private String lotStatus;
    private String startBid;
    private String lastBid;
    private String time;

}
