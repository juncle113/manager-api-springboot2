package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 管理员登录异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AdminLoginException extends RuntimeException {

    private ErrorInfo errorInfo;

    public AdminLoginException() {
        super(ErrorEnum.ADMIN_LOGIN_ERROR.getMessage());
        this.errorInfo = new ErrorInfo(ErrorEnum.ADMIN_LOGIN_ERROR.getCode(), ErrorEnum.ADMIN_LOGIN_ERROR.getMessage());
    }
}
