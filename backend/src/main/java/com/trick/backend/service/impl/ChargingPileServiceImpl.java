package com.trick.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trick.backend.common.result.PageResult;
import com.trick.backend.mapper.ChargingPileMapper;
import com.trick.backend.model.dto.ChargingPileAddAndUpdateDTO;
import com.trick.backend.model.dto.ChargingPileQueryDTO;
import com.trick.backend.model.pojo.ChargingPile;
import com.trick.backend.model.vo.ChargingPileVO;
import com.trick.backend.service.ChargingPileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChargingPileServiceImpl implements ChargingPileService {
    @Autowired
    private ChargingPileMapper chargingPileMapper;

    //分页条件查询
    @Override
    public PageResult<ChargingPile> getChargingPilesByPage(ChargingPileQueryDTO chargingPileQueryDTO) {
        PageHelper.startPage(chargingPileQueryDTO.getPageNum(), chargingPileQueryDTO.getPageSize());
        List<ChargingPile> list = chargingPileMapper.getAllChargingPiles(chargingPileQueryDTO);
        PageInfo<ChargingPile> pageInfo = new PageInfo<>(list);

        List<ChargingPile> records = pageInfo.getList();
        long total = pageInfo.getTotal();

        return new PageResult<>(total, records);
    }

    // 根据ID查询
    @Override
    public ChargingPileVO getChargingPileById(Integer id) {
        ChargingPileVO chargingPileVO = new ChargingPileVO();
        ChargingPile chargingPile = chargingPileMapper.getChargingPileById(id);

        BeanUtils.copyProperties(chargingPile, chargingPileVO);

        return chargingPileVO;
    }

    //添加充电桩
    @Override
    public void addChargingPile(ChargingPileAddAndUpdateDTO chargingAddPileDTO) {
        LocalDateTime now = LocalDateTime.now();
        chargingAddPileDTO.setCreateTime(now);
        chargingAddPileDTO.setUpdateTime(now);

        chargingPileMapper.addChargingPile(chargingAddPileDTO);
    }

    //更新充电桩
    @Override
    public void updateChargingPile(Integer id, ChargingPileAddAndUpdateDTO chargingUpdatePileDTO) {
        chargingUpdatePileDTO.setId(id);
        LocalDateTime now = LocalDateTime.now();
        chargingUpdatePileDTO.setUpdateTime(now);

        chargingPileMapper.updateChargingPile(chargingUpdatePileDTO);
    }
}
