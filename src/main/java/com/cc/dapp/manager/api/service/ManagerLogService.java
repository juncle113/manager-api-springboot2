package com.cc.dapp.manager.api.service;

public interface ManagerLogService {

    void log(Integer byAdminId, String remark);

    void log(String byAdminName, String remark);
}
