package com.trick.backend.controller.wx;

import com.trick.backend.common.result.Result;
import com.trick.backend.model.dto.ChargingDTO;
import com.trick.backend.service.ChargingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/charging")
public class WxChargingController {
    @Autowired
    private ChargingOrderService chargingOrderService;


    @PostMapping("/start")
    public Result<String> startCharging(@RequestBody ChargingDTO chargingDTO) {
        // token获取id
        // Integer id = UserContext.getUserId();
        Integer id = 20;
        return Result.success(chargingOrderService.startCharging(id, chargingDTO.getPileId()));
    }
}
