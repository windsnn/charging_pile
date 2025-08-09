package com.trick.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trick.backend.common.result.PageResult;
import com.trick.backend.mapper.FaultReportMapper;
import com.trick.backend.model.dto.FaultReportQueryDTO;
import com.trick.backend.model.dto.FaultReportUpdateDTO;
import com.trick.backend.model.pojo.FaultReport;
import com.trick.backend.model.vo.FaultReportVO;
import com.trick.backend.service.FaultReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FaultReportServiceImpl implements FaultReportService {
    @Autowired
    private FaultReportMapper faultReportMapper;

    //分页条件查询
    @Override
    public PageResult<FaultReportVO> getFaultReportsByPage(FaultReportQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<FaultReportVO> list = faultReportMapper.getAllFaultReport(queryDTO);
        PageInfo<FaultReportVO> pageInfo = new PageInfo<>(list);

        List<FaultReportVO> records = pageInfo.getList();
        long total = pageInfo.getTotal();

        return new PageResult<>(total, records);
    }

    //根据ID更新处理状态（暂定）
    @Override
    public void updateFaultReport(Integer id, FaultReportUpdateDTO updateDTO) {
        updateDTO.setId(id);
        updateDTO.setUpdateTime(LocalDateTime.now());

        faultReportMapper.updateFaultReport(updateDTO);
    }
}
