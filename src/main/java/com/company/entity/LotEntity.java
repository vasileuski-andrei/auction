package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LotEntity {

    private Integer id;
    private String lotName;
    private String owner;
    private LotStatus lotStatus;
}
