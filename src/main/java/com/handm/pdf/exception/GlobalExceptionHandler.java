package com.handm.pdf.exception;

import com.handm.pdf.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.handm.pdf.util.ResultUtil.notLoginError;

/**
 * @author lqm
 * @date 2024/1/6 17:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = LoginException.class)
    public ResultUtil.Result loginExceptionHandler(LoginException e) {
        return notLoginError(e.getMessage());
    }
}
