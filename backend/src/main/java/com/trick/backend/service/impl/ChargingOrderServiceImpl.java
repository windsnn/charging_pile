package com.trick.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trick.backend.common.WebSocketSimulate.ChargingSimulationService;
import com.trick.backend.common.exception.BusinessException;
import com.trick.backend.common.result.PageResult;
import com.trick.backend.mapper.ChargingOrderMapper;
import com.trick.backend.mapper.ChargingPileMapper;
import com.trick.backend.mapper.TransactionLogMapper;
import com.trick.backend.mapper.UserMapper;
import com.trick.backend.model.dto.ChargingDTO;
import com.trick.backend.model.dto.ChargingOrderAddDTO;
import com.trick.backend.model.dto.ChargingPileAddAndUpdateDTO;
import com.trick.backend.model.pojo.ChargingOrder;
import com.trick.backend.model.pojo.ChargingPile;
import com.trick.backend.model.pojo.TransactionLog;
import com.trick.backend.model.vo.ChargingOrderVO;
import com.trick.backend.model.vo.ChargingPileVO;
import com.trick.backend.service.ChargingOrderService;
import com.trick.backend.service.ChargingPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChargingOrderServiceImpl implements ChargingOrderService {
    @Autowired
    private ChargingOrderMapper orderMapper;

    @Override
    //当前用户正在进行的订单
    public List<ChargingOrderVO> getOngoing(Integer userId) {
        return orderMapper.getOngoing(userId);
    }

    @Override
    public PageResult<ChargingOrderVO> getPagedOrder(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChargingOrderVO> list = orderMapper.getAllOrderByUserId(userId);
        PageInfo<ChargingOrderVO> pageInfo = new PageInfo<>(list);

        List<ChargingOrderVO> records = pageInfo.getList();
        long total = pageInfo.getTotal();

        return new PageResult<>(total, records);
    }
}
