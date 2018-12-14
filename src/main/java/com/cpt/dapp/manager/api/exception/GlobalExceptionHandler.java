package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.constant.ErrorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author sunli
 * @date 2018/12/07
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity handleBusinessException(BusinessException e) {
        logger.warn(ErrorConstant.BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.BAD_REQUEST);
    }

    /**
     * validator异常处理（参数非对象的场合）
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({AdminLoginException.class})
    public ResponseEntity handleLoginException(AdminLoginException e) {
        logger.warn(ErrorConstant.LOGIN, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * validator异常处理（参数非对象的场合）
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({AuthorizedException.class})
    public ResponseEntity handleAuthorizedException(AuthorizedException e) {
        logger.warn(ErrorConstant.AUTHORIZED, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.FORBIDDEN);
    }

    /**
     * validator异常处理（参数非对象的场合）
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({AccountExistedException.class})
    public ResponseEntity handleAccountExistedException(AccountExistedException e) {
        logger.warn(ErrorConstant.BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.CONFLICT);
    }

    /**
     * validator异常处理（参数非对象的场合）
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity handleDataNotFoundException(DataNotFoundException e) {
        logger.warn(ErrorConstant.BUSINESS, e);
        return new ResponseEntity(e.getErrorInfo(), HttpStatus.NOT_FOUND);
    }

    /**
     * validator异常处理（参数非对象的场合）
     *
     * @param e 异常类型
     * @return 响应结果
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(ErrorConstant.PARAMETER, e);
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * validator异常处理（参数为对象的场合）
     * 重写父类方法，捕获MethodArgumentNotValidException
     *
     * @param ex      异常类型
     * @param headers 响应头信息
     * @param status  响应状态码
     * @param request 请求信息
     * @return 响应结果
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // 设置错误信息
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        String errorMessage;
        for (FieldError fieldError : fieldErrors) {
            errorMessage = fieldError.getField().concat(":").concat(fieldError.getDefaultMessage());
            errorMessages.add(errorMessage);
        }

        return this.handleExceptionInternal(ex, errorMessages, headers, status, request);
    }
}
