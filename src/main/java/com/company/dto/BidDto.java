package com.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BidDto {

    private Integer id;
    private String lotName;
    private Integer lotId;
    private String userName;
    private Integer userBid;
    private String dateTime;
}
