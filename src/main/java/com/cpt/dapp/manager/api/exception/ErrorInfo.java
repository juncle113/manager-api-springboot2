package com.cpt.dapp.manager.api.exception;

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

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
