package com.trick.backend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaultReportVO {

    private Integer id;
    private Integer userId;
    private Integer pileId;
    private String faultType;
    private String description;
    private String images;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String pileNo;//充电桩编号
    private String nickname;//用户名
    private String phone;//用户手机号

}
