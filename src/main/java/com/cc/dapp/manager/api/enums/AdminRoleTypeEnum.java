package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum AdminRoleTypeEnum {

    ROOT(1, "超级管理员"),
    ADMIN(2, "普通管理员");

    private int code;
    private String name;

    AdminRoleTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(int code) {
        for (AdminRoleTypeEnum item : AdminRoleTypeEnum.values()) {
            if (code == item.getCode()) {
                return item.getName();
            }
        }
        return null;
    }
}