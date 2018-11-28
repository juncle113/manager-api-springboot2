package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class LoginException extends RuntimeException{

    private ErrorInfo errorInfo;

    public LoginException(ErrorCodeEnum e) {
        super(e.getMessage());

        this.errorInfo = new ErrorInfo(e.getCode(), e.getMessage());
    }
}
