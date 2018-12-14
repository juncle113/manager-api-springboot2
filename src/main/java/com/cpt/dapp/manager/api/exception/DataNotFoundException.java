package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 数据未找到异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class DataNotFoundException extends BaseException {

    public DataNotFoundException() {
        super(ErrorEnum.DATA_NOT_FOUND);
    }
}
