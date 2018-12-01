package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class AccountHasExistedException extends RuntimeException{

    private ErrorInfo errorInfo;

    public AccountHasExistedException() {
        super(ErrorCodeEnum.ACCOUNT_HAS_EXISTED.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.ACCOUNT_HAS_EXISTED.getCode(), ErrorCodeEnum.ACCOUNT_HAS_EXISTED.getMessage());
    }
}
