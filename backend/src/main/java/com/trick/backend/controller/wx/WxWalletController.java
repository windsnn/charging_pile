package com.trick.backend.controller.wx;

import com.trick.backend.common.result.PageResult;
import com.trick.backend.common.result.Result;
import com.trick.backend.mapper.TransactionLogMapper;
import com.trick.backend.mapper.UserMapper;
import com.trick.backend.model.dto.RechargeDTO;
import com.trick.backend.model.pojo.TransactionLog;
import com.trick.backend.service.TransactionLogService;
import com.trick.backend.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/wallet")
public class WxWalletController {
    @Autowired
    private TransactionLogService transactionLogService;
    @Autowired
    private UserService userService;

    @GetMapping
    public Result<Double> getWallet() {
        // token获取id
        // Integer id = UserContext.getUserId();
        Integer id = 20;
        return Result.success(userService.getWallet(id));
    }

    @GetMapping("/transactions")
    public Result<PageResult<TransactionLog>> getPagedTransactions(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        // token获取id
        // Integer id = UserContext.getUserId();
        Integer id = 10;
        return Result.success(transactionLogService.getPagedTransactions(id, pageNum, pageSize));
    }

    @PostMapping("/recharge")
    public Result<?> recharge(@RequestBody RechargeDTO rechageDTO) {
        // token获取id
        // Integer id = UserContext.getUserId();
        Integer id = 10;
        transactionLogService.recharge(id, rechageDTO.getAmount());
        return Result.success();
    }

}
