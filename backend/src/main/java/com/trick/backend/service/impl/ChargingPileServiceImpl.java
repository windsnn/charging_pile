package com.trick.backend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trick.backend.common.result.PageResult;
import com.trick.backend.mapper.ChargingPileMapper;
import com.trick.backend.model.dto.ChargingPileAddAndUpdateDTO;
import com.trick.backend.model.dto.ChargingPileQueryDTO;
import com.trick.backend.model.pojo.ChargingPile;
import com.trick.backend.model.vo.ChargingPileVO;
import com.trick.backend.service.ChargingPileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChargingPileServiceImpl implements ChargingPileService {
    @Value("${tx.apiKey}")
    private String apiKey;
    @Value("${tx.apiUrl}")
    private String apiUrl;

    @Autowired
    private ChargingPileMapper chargingPileMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;

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
        chargingUpdatePileDTO.setUpdateTime(LocalDateTime.now());

        chargingPileMapper.updateChargingPile(chargingUpdatePileDTO);
    }

    //删除充电桩
    @Override
    public void deleteChargingPile(Integer id) {
        chargingPileMapper.deleteCharging(id);
    }

    //获取距离当前位置10公里以内的充电桩
    //寻找最近的5个充电桩
    @Override
    public List<ChargingPileVO> nearbyByRoadDistance(Double latitude, Double longitude) {
        Double maxStraightDistanceInMeters = 10000.0;
        Integer maxPiles = 10;

        // 1. 查询10公里内的候选充电桩,最多10个
        List<ChargingPile> list = chargingPileMapper.getNearbyByStraightDistance(latitude, longitude, maxStraightDistanceInMeters, maxPiles);

        List<ChargingPileVO> candidates = list.stream()
                .map(pile -> {
                    ChargingPileVO vo = new ChargingPileVO();
                    BeanUtils.copyProperties(pile, vo);
                    return vo;
                })
                .toList();

        // 2. 调用腾讯地图 API 获取道路距离
        for (ChargingPileVO pile : candidates) {
            try {
                String url = String.format(apiUrl, latitude, longitude, pile.getLatitude(), pile.getLongitude(), apiKey);

                String jsonResponse = restTemplate.getForObject(url, String.class);
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                if (jsonNode.get("status").asInt() == 0) {
                    double roadDistance = jsonNode.get("result").get("routes").get(0).get("distance").asDouble();
                    pile.setRoadDistance(roadDistance);
                } else {
                    pile.setRoadDistance(Double.MAX_VALUE);
                }
            } catch (Exception e) {
                pile.setRoadDistance(Double.MAX_VALUE);
                log.error("调用腾讯地图 API 获取道路距离失败", e);
            }
        }

        // 3. 排序 + 取前5个返回
        return candidates.stream()
                .filter(p -> p.getRoadDistance() != null && p.getRoadDistance() < Double.MAX_VALUE)
                .sorted(Comparator.comparingDouble(ChargingPileVO::getRoadDistance))
                .limit(5)
                .collect(Collectors.toList());
    }

}
