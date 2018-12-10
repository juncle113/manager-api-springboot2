package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 鉴权异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AuthorizedException extends RuntimeException {

    private ErrorInfo errorInfo;

    public AuthorizedException() {
        super(ErrorEnum.PERMISSION_NO_ACCESS.getMessage());
        this.errorInfo = new ErrorInfo(ErrorEnum.PERMISSION_NO_ACCESS.getCode(), ErrorEnum.PERMISSION_NO_ACCESS.getMessage());
    }
}
