package com.cpt.dapp.manager.api.exception;

import com.cpt.dapp.manager.api.enums.ErrorEnum;
import lombok.Data;

/**
 * 数据已存在异常
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class DataExistedException extends RuntimeException {

    private ErrorInfo errorInfo;

    public DataExistedException() {
        super(ErrorEnum.DATA_EXISTED.getMessage());
        this.errorInfo = new ErrorInfo(ErrorEnum.DATA_EXISTED.getCode(), ErrorEnum.DATA_EXISTED.getMessage());
    }
}
