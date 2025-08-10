package com.trick.backend.controller.wx;

import com.trick.backend.common.result.Result;
import com.trick.backend.model.vo.ChargingPileVO;
import com.trick.backend.service.ChargingPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/piles")
public class WxChargingPileController {
    @Autowired
    private ChargingPileService chargingPileService;

    @GetMapping("/nearby")
    public Result<List<ChargingPileVO>> nearby(Double latitude, Double longitude) {
       return Result.success(chargingPileService.nearbyByRoadDistance(latitude, longitude)) ;
    }
}
