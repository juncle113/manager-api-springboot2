package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum AuthTypeEnum {

    READ_ONLY("只读权限"),
    WRITE("可写权限");

    private String message;

    AuthTypeEnum(String message) {
        this.message = message;
    }
}