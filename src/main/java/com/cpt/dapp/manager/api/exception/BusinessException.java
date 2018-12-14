package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 业务异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class BusinessException extends BaseException {

    public BusinessException(ErrorEnum e) {
        super(e);
    }
}
