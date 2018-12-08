package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

@Data
public class AuthorizedException2 extends RuntimeException {

    private ErrorInfo errorInfo;

    public AuthorizedException2() {
        super(ErrorEnum.USER_ACCOUNT_FORBIDDEN.getMessage());

        this.errorInfo = new ErrorInfo(ErrorEnum.USER_ACCOUNT_FORBIDDEN.getCode(), ErrorEnum.USER_ACCOUNT_FORBIDDEN.getMessage());
    }
}
