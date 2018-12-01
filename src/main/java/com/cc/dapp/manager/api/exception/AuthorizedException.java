package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class AuthorizedException extends RuntimeException{

    private ErrorInfo errorInfo;

    public AuthorizedException() {
        super(ErrorCodeEnum.PERMISSION_NO_ACCESS.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.PERMISSION_NO_ACCESS.getCode(), ErrorCodeEnum.PERMISSION_NO_ACCESS.getMessage());
    }
}
