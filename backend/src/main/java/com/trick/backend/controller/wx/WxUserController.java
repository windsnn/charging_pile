package com.trick.backend.controller.wx;

import com.trick.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/user")
public class WxUserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/profile")
}
