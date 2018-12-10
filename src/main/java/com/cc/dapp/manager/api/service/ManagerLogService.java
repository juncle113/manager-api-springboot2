package com.cc.dapp.manager.api.service;

/**
 * 管理日志Service
 *
 * @author sunli
 * @date 2018/12/07
 */
public interface ManagerLogService {

    void log(Integer byAdminId, String remark);

    void log(String byAdminName, String remark);
}
