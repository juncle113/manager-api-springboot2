package com.cc.dapp.manager.api.exception;

import lombok.Data;

import java.io.Serializable;

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
