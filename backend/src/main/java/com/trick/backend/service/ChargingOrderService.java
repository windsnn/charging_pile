package com.trick.backend.service;

import com.trick.backend.model.dto.ChargingDTO;
import com.trick.backend.model.vo.ChargingOrderVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChargingOrderService {
    String startCharging(Integer UserId, ChargingDTO chargingDTO);

    //核心逻辑（用户主动结束充电）
    String stopChargingByUser(Integer userId, ChargingDTO chargingDTO);

    //核心逻辑（余额不足停止充电）
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void stopChargingDueToInsufficientBalance(Integer userId, String orderNo);

    //核心逻辑（订单结算服务）
    String finalizeCharging(Integer userId, String orderNo);

    //当前用户正在进行的订单
    List<ChargingOrderVO> getOngoing(Integer userId);
}
