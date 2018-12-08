package com.cc.dapp.manager.api.enums;

import lombok.Getter;

/**
 * 管理员角色类型
 *
 * @author sunli
 * @date 2018/12/07
 */
@Getter
public enum AdminRoleEnum {

    ROOT(1, "系统管理员"),
    SUPER_ADMIN(2, "超级管理员"),
    ADMIN(3, "普通管理员");

    private int code;
    private String name;

    /**
     * 管理员角色类型的构造方法
     *
     * @param code 类型编码
     * @param name 类型名称
     */
    AdminRoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 取得管理员角色类型编码
     *
     * @param code 类型编码
     * @return 类型名称
     */
    public static String getNameByCode(int code) {
        for (AdminRoleEnum item : AdminRoleEnum.values()) {
            if (code == item.getCode()) {
                return item.getName();
            }
        }
        return null;
    }
}