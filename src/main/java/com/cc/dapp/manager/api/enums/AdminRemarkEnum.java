package com.cc.dapp.manager.api.enums;

import lombok.Getter;

@Getter
public enum AdminRemarkEnum {

    LOGIN_SUCCESS("登录成功"),
    LOGIN_FAILED("登录失败！用户名或密码错误"),
    LOGIN_FORBIDDEN("登录失败！账号已被禁用"),
    MODIFY_ADMIN_INFO("修改管理员信息"),
    MODIFY_USER_INFO("修改用户信息");

    private String message;

    AdminRemarkEnum(String message) {
        this.message = message;
    }
}