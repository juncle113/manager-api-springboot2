package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class AuthorizedException extends RuntimeException{

    private ErrorInfo errorInfo;

    public AuthorizedException() {
        super(ErrorCodeEnum.ADMIN_LOGIN_ERROR.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.ADMIN_LOGIN_ERROR.getCode(), ErrorCodeEnum.ADMIN_LOGIN_ERROR.getMessage());
    }
}