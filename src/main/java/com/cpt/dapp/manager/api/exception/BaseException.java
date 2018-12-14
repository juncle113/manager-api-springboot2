package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * Base异常
 *
 * @author sunli
 * @date 2018/12/14
 */
@Data
public class BaseException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorInfo = new ErrorInfo(errorEnum.getCode(), errorEnum.getMessage());
    }
}
