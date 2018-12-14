package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 鉴权异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class AuthorizedException extends BaseException {

    public AuthorizedException() {
        super(ErrorEnum.PERMISSION_NO_ACCESS);
    }
}
