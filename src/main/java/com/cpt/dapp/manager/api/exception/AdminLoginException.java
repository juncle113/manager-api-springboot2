package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 管理员登录异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AdminLoginException extends BaseException {

    public AdminLoginException() {
        super(ErrorEnum.ADMIN_LOGIN_ERROR);
    }
}
