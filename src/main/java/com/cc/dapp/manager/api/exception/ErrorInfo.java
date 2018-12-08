package com.cc.dapp.manager.api.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * 错误信息
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
