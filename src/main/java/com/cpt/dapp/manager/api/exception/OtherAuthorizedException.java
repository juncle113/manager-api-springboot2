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
public class OtherAuthorizedException extends BaseException {

    public OtherAuthorizedException() {
        super(ErrorEnum.USER_ACCOUNT_FORBIDDEN);
    }
}
