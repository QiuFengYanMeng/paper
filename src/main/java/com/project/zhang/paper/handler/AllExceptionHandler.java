package com.project.zhang.paper.handler;

import com.project.zhang.paper.back.Back;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *     全局统一异常处理
 * </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 */
@RestControllerAdvice
@Slf4j
public class AllExceptionHandler {

    /**
     * 所有异常报错
     *
     * @param exception
     * @return String
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Back allExceptionHandler(Exception exception) {
        exception.printStackTrace();
        log.error("程序异常:", exception);
        return new Back().runError("程序异常，请联系工作人员");
    }
}
