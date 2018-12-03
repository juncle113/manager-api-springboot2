package com.cc.dapp.manager.api.exception;

import com.cc.dapp.manager.api.enums.ErrorCodeEnum;
import lombok.Data;

@Data
public class DataExistedException extends RuntimeException{

    private ErrorInfo errorInfo;

    public DataExistedException() {
        super(ErrorCodeEnum.DATA_EXISTED.getMessage());

        this.errorInfo = new ErrorInfo(ErrorCodeEnum.DATA_EXISTED.getCode(), ErrorCodeEnum.DATA_EXISTED.getMessage());
    }
}
