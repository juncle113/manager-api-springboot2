package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity handleBusinessException(BusinessException e) {
        logger.warn(Constant.LOG_TYPE_BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ LoginException.class })
    public ResponseEntity handleLoginException(LoginException e) {
        logger.warn(Constant.LOG_TYPE_LOGIN, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.UNAUTHORIZED);
    }


}
