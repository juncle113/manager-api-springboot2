package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 账号已存在异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AccountExistedException extends RuntimeException {

    private ErrorInfo errorInfo;

    public AccountExistedException() {
        super(ErrorEnum.ACCOUNT_EXISTED.getMessage());

        this.errorInfo = new ErrorInfo(ErrorEnum.ACCOUNT_EXISTED.getCode(), ErrorEnum.ACCOUNT_EXISTED.getMessage());
    }
}
