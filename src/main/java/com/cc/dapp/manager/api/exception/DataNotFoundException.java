package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException{

    private ErrorInfo errorInfo;

    public DataNotFoundException() {
        super(ErrorCodeEnum.DATA_NOT_FOUND.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.DATA_NOT_FOUND.getCode(), ErrorCodeEnum.DATA_NOT_FOUND.getMessage());
    }
}
