package com.rjgc.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:38
 * <p>
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public ResBody<BizException> bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.debug("Message: " + e.getMessage());
        log.debug("Cause: " + e.getCause());
        return ResBody.error(e);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResBody<NullPointerException> nullPointerExceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.debug("Message: " + e.getMessage());
        log.debug("Cause: " + e.getCause());
        return ResBody.error(50001, "空指针异常");
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResBody<DuplicateKeyException> duplicateKeyExceptionHandler(HttpServletRequest req, DuplicateKeyException e) {
        log.debug("Message: " + e.getMessage());
        log.debug("Cause: " + e.getCause());
        return ResBody.error(50002, "id重复");
    }

    @ExceptionHandler(value = Exception.class)
    public ResBody<Exception> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Message: " + e.getMessage());
        log.error("Cause: " + e.getCause());
        return ResBody.error(50002, "内部异常");
    }
}
