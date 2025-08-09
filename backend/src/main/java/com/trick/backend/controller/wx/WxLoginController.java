package com.trick.backend.controller.wx;

import com.trick.backend.common.result.Result;
import com.trick.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wx/auth")
public class WxLoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String code) throws Exception {
        return Result.success(loginService.loginUser(code));
    }

}
