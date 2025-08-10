package com.trick.backend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserProfileVO {

    private String nickname;
    private String avatarUrl;
    private Double balance;
    private String phone;

}
