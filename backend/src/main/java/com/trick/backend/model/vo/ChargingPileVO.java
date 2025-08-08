package com.trick.backend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargingPileVO {

    private Integer id;
    private String pileNo;
    private Integer type;
    private Integer status;
    private Double powerRate;
    private Double pricePerKwh;
    private String locationDesc;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
