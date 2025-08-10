package com.trick.backend.controller.wx;

import com.trick.backend.common.result.Result;
import com.trick.backend.model.dto.UserAddAndUpdateDTO;
import com.trick.backend.model.vo.WxUserProfileVO;
import com.trick.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/user/profile")
public class WxUserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result<WxUserProfileVO> getUserProfile() {
        //获取token id
        // Integer id = UserContext.getUserId();
        Integer id = 1;

        return Result.success(userService.getUserProfileById(id));
    }

    @PutMapping
    public Result<?> updateUserProfile(@RequestBody UserAddAndUpdateDTO userAddAndUpdateDTO) {
        //获取token id
        // Integer id = UserContext.getUserId();
        Integer id = 20;

        userAddAndUpdateDTO.setId(id);
        userService.updateUser(userAddAndUpdateDTO);
        return Result.success();
    }
}
