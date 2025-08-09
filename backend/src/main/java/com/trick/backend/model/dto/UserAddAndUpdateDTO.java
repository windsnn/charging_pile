package com.trick.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddAndUpdateDTO {

    private Integer id;
    private String openid;
    private String nickname;
    private String avatarUrl;
    private Double balance;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
