package com.cc.dapp.manager.api.service;

/**
 * 管理日志Service
 *
 * @author sunli
 * @date 2018/12/07
 */
public interface ManagerLogService {

    /**
     * 保存日志（记录管理员id）
     *
     * @param byAdminId 当前管理员id
     * @param remark 操作备注
     */
    void log(Integer byAdminId, String remark);

    /**
     * 保存日志（未取得管理员id的场合，记录登录时用户名）
     *
     * @param byAdminName 当前管理员用户名
     * @param remark 操作备注
     */
    void log(String byAdminName, String remark);
}
