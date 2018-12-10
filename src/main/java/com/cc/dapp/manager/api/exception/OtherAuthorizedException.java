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
public class OtherAuthorizedException extends RuntimeException {

    private ErrorInfo errorInfo;

    public OtherAuthorizedException() {
        super(ErrorEnum.USER_ACCOUNT_FORBIDDEN.getMessage());
        this.errorInfo = new ErrorInfo(ErrorEnum.USER_ACCOUNT_FORBIDDEN.getCode(), ErrorEnum.USER_ACCOUNT_FORBIDDEN.getMessage());
    }
}
