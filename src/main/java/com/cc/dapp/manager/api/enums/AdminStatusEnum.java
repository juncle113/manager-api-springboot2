package com.cc.dapp.manager.api.enums;

import lombok.Getter;

/**
 * 管理员状态类型
 *
 * @author sunli
 * @date 2018/12/07
 */
@Getter
public enum AdminStatusEnum {

    // 对应ManagerAdmin的status属性
    VALID(1, "有效"),
    INVALID(2, "无效");

    private int code;
    private String name;

    /**
     * 管理员状态类型的构造方法
     *
     * @param code 类型编码
     * @param name 类型名称
     */
    AdminStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 取得管理员状态类型编码
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