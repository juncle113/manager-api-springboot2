package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.constant.ErrorConstant;
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
        logger.warn(ErrorConstant.BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AdminLoginException.class })
    public ResponseEntity handleLoginException(AdminLoginException e) {
        logger.warn(ErrorConstant.LOGIN, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ AuthorizedException.class })
    public ResponseEntity handleLoginException(AuthorizedException e) {
        logger.warn(ErrorConstant.AUTHORIZED, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ AccountExistedException.class })
    public ResponseEntity handleLoginException(AccountExistedException e) {
        logger.warn(ErrorConstant.BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.CONFLICT);
    }


}
