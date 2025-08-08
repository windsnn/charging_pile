package com.trick.backend.mapper;

import com.trick.backend.model.dto.ChargingPileAddAndUpdateDTO;
import com.trick.backend.model.dto.ChargingPileQueryDTO;
import com.trick.backend.model.pojo.ChargingPile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChargingPileMapper {
    //分页条件查询充电桩（编号，状态，地点），排序方式默认为更新时间倒序
    //获取完整的数据，使用pageHelper进行分页
    List<ChargingPile> getAllChargingPiles(ChargingPileQueryDTO chargingPileQueryDTO);

    //根据ID获取单个充电桩详情
    ChargingPile getChargingPileById(Integer id);

    //新增充电桩数据
    void addChargingPile(ChargingPileAddAndUpdateDTO chargingAddPileDTO);

    //根据ID更新充电桩数据
    void updateChargingPile(ChargingPileAddAndUpdateDTO chargingUpdatePileDTO);
}
