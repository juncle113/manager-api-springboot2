package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 账号已存在异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AccountExistedException extends BaseException {

    public AccountExistedException() {
        super(ErrorEnum.ACCOUNT_EXISTED);
    }
}
