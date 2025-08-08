package com.trick.backend.common.exception;

import com.trick.backend.common.result.Result;
import com.trick.backend.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 处理 BusinessException
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理其他未捕获的异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleGeneralException(Exception e) {
        log.error("未知异常；{}", e.getMessage(), e);
        return Result.error(ResultCode.ERROR.getCode(), "服务器异常，请稍后再试");
    }
}
