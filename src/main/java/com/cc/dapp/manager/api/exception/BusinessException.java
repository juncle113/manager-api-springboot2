package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 业务异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class BusinessException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BusinessException(ErrorEnum e) {
        super(e.getMessage());
        this.errorInfo = new ErrorInfo(e.getCode(), e.getMessage());
    }
}
