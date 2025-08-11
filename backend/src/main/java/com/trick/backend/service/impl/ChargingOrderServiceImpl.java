package com.trick.backend.service.impl;

import com.trick.backend.common.exception.BusinessException;
import com.trick.backend.service.ChargingOrderService;
import com.trick.backend.service.ChargingPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargingOrderServiceImpl implements ChargingOrderService {
    @Autowired
    private ChargingPileService chargingPileService;

    //核心业务（充电逻辑）
    @Override
    public String startCharging(Integer id, String pileId) {
        if (pileId == null || pileId.isEmpty()) {
            throw new BusinessException("充电桩不能为空");
        }

        if (chargingPileService.getChargingPileByPileId(pileId) == null) {
            throw new BusinessException("无效的充电桩");
        }

        


        return "";
    }
}
