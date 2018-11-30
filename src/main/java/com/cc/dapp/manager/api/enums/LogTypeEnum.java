package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum LogTypeEnum {

    BUSINESS("业务问题"),
    LOGIN("登录问题"),
    AUTHORIZED("权限问题");

    private String message;

    LogTypeEnum(String message) {
        this.message = message;
    }
}