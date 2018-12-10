package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 数据未找到异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class DataNotFoundException extends RuntimeException {

    private ErrorInfo errorInfo;

    public DataNotFoundException() {
        super(ErrorEnum.DATA_NOT_FOUND.getMessage());
        this.errorInfo = new ErrorInfo(ErrorEnum.DATA_NOT_FOUND.getCode(), ErrorEnum.DATA_NOT_FOUND.getMessage());
    }
}
