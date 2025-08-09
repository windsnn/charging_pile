package com.trick.backend.controller.admin;

import com.trick.backend.common.result.PageResult;
import com.trick.backend.common.result.Result;
import com.trick.backend.model.dto.ChargingPileQueryDTO;
import com.trick.backend.model.dto.FaultReportQueryDTO;
import com.trick.backend.model.dto.FaultReportUpdateDTO;
import com.trick.backend.model.pojo.ChargingPile;
import com.trick.backend.model.pojo.FaultReport;
import com.trick.backend.model.vo.FaultReportVO;
import com.trick.backend.service.FaultReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/faults")
public class FaultReportController {
    @Autowired
    FaultReportService faultReportService;

    @GetMapping()
    Result<PageResult<FaultReportVO>> getFaultReportsByPage(FaultReportQueryDTO queryDTO) {
        return Result.success(faultReportService.getFaultReportsByPage(queryDTO));
    }

    @PutMapping("{id}")
    Result<?> updateFaultReport(@PathVariable Integer id, @RequestBody FaultReportUpdateDTO updateDTO) {
        faultReportService.updateFaultReport(id, updateDTO);
        return Result.success();
    }
}
