package com.trick.backend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trick.backend.common.exception.BusinessException;
import com.trick.backend.common.result.ResultCode;
import com.trick.backend.common.utils.JwtUtil;
import com.trick.backend.model.dto.UserAddAndUpdateDTO;
import com.trick.backend.service.LoginService;
import com.trick.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    // 如果有 Redis，可以注入缓存 session_key
    // @Autowired
    // private RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String loginUser(String code) throws JsonProcessingException {
        // 1. 调用微信 API 获取 openid & session_key
        String url = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";

        String response = restTemplate.getForObject(url, String.class, appid, secret, code);
        log.info("微信登录返回: {}", response);
        log.info("code: {}", code);

        JsonNode jsonNode = objectMapper.readTree(response);

        if (jsonNode.has("errcode")) {
            String errMsg = jsonNode.get("errmsg").asText();
            log.error("微信登录失败: {}", errMsg);
            throw new BusinessException(1001, "微信登录失败");
        }

        String openid = jsonNode.get("openid").asText(null);
        String sessionKey = jsonNode.get("session_key").asText(null);

        if (openid == null) {
            throw new BusinessException(1001, "微信登录失败：未获取到openid");
        }

        // 2. 创建或更新用户，并生成 token
        Integer id = userService.getUserByOpenid(openid);
        LocalDateTime now = LocalDateTime.now();

        if (id == null) {
            UserAddAndUpdateDTO dto = new UserAddAndUpdateDTO();
            dto.setOpenid(openid);
            dto.setCreateTime(now);
            dto.setUpdateTime(now);
            id = userService.addUser(dto);
            log.info("新用户注册成功: openid={}", openid);
        }

        // 如果需要缓存 session_key 以便后续解密手机号等
        // redisTemplate.opsForValue().set("wx:sessionKey:" + openid, sessionKey, 2, TimeUnit.HOURS);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("openid", openid);

        return jwtUtil.getToken(claims);
    }
}