package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum AdminRoleEnum {

    ROOT(1, "系统管理员"),
    SUPER_ADMIN(2, "超级管理员"),
    ADMIN(3, "普通管理员");

    private int code;
    private String name;

    AdminRoleEnum(int code, String name) {
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