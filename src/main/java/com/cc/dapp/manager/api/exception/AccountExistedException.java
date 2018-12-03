package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class AccountExistedException extends RuntimeException{

    private ErrorInfo errorInfo;

    public AccountExistedException() {
        super(ErrorCodeEnum.ACCOUNT_EXISTED.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.ACCOUNT_EXISTED.getCode(), ErrorCodeEnum.ACCOUNT_EXISTED.getMessage());
    }
}
