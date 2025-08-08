package com.trick.backend.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private Double balance;
    private String mobile;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
