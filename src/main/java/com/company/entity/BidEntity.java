package com.company.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidEntity {

    private Integer id;
    private String lotName;
    private Integer lotId;
    private String userName;
    private Integer userBid;
    private LocalDateTime dateTime;
}
