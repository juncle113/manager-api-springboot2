package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class AuthorizedException2 extends RuntimeException {

    private ErrorInfo errorInfo;

    public AuthorizedException2() {
        super(ErrorCodeEnum.USER_ACCOUNT_FORBIDDEN.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.USER_ACCOUNT_FORBIDDEN.getCode(), ErrorCodeEnum.USER_ACCOUNT_FORBIDDEN.getMessage());
    }
}
