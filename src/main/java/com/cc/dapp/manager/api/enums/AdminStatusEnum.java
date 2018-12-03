package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum AdminStatusEnum {

    VALID(1, "有效"),
    INVALID(2, "无效");

    private int code;
    private String name;

    AdminStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(int code) {
        for (AdminRoleEnum item : AdminRoleEnum.values()) {
            if (code == item.getCode()) {
                return item.getName();
            }
        }
        return null;
    }
}