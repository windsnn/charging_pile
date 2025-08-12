package com.trick.backend.controller.wx;

import com.trick.backend.common.result.Result;
import com.trick.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx/auth")
public class WxLoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestParam String code) throws Exception {
        String token = loginService.loginUser(code);

        Map<String, String> params = new HashMap<>();
        params.put("User-Token", token);

        return Result.success(params);
    }

}
