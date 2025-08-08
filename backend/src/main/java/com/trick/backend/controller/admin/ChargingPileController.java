package com.trick.backend.controller.admin;

import com.trick.backend.common.result.PageResult;
import com.trick.backend.common.result.Result;
import com.trick.backend.model.dto.ChargingPileAddAndUpdateDTO;
import com.trick.backend.model.dto.ChargingPileQueryDTO;
import com.trick.backend.model.pojo.ChargingPile;
import com.trick.backend.model.vo.ChargingPileVO;
import com.trick.backend.service.ChargingPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/piles")
public class ChargingPileController {
    @Autowired
    private ChargingPileService chargingPileService;

    @GetMapping()
    Result<PageResult<ChargingPile>> getChargingPilesByPage(ChargingPileQueryDTO queryDTO) {
        return Result.success(chargingPileService.getChargingPilesByPage(queryDTO));
    }

    @GetMapping("/{id}")
    Result<ChargingPileVO> getChargingPileById(@PathVariable Integer id) {
        return Result.success(chargingPileService.getChargingPileById(id));
    }

    @PostMapping()
    Result<?> addChargingPile(@RequestBody ChargingPileAddAndUpdateDTO chargingAddPileDTO) {
        chargingPileService.addChargingPile(chargingAddPileDTO);
        return Result.success();
    }

    @PutMapping("/{id}")
    Result<?> updateChargingPile(@PathVariable Integer id, @RequestBody ChargingPileAddAndUpdateDTO chargingUpdatePileDTO) {
        chargingPileService.updateChargingPile(id, chargingUpdatePileDTO);
        return Result.success();
    }

}
