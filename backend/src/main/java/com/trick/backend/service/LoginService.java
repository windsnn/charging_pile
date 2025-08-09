package com.trick.backend.service;

import com.trick.backend.model.vo.WxLoginVO;

public interface LoginService {
    //微信用户登录
    String loginUser(String code) throws Exception;
}
