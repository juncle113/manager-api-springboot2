package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.constant.Constant;
import com.cc.dapp.manager.api.exception.DataNotFoundException;
import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import com.cc.dapp.manager.api.model.domain.ManagerLog;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import com.cc.dapp.manager.api.repository.ManagerLogRepository;
import com.cc.dapp.manager.api.service.ManagerLogService;
import com.cc.dapp.manager.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerLogServiceImpl implements ManagerLogService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Autowired
    private ManagerLogRepository managerLogRepository;

    @Override
    public void log(Integer byAdminId, String remark) {

        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(byAdminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        ManagerLog managerLog = new ManagerLog();
        managerLog.setId(managerAdmin.getId());
        managerLog.setRemark(editLogRemark(managerAdmin.getUserName(), remark));
        managerLog.setCreatedTime(DateUtil.now());
        managerLogRepository.save(managerLog);
    }

    @Override
    public void log(String byAdminName, String remark) {
        ManagerLog managerLog = new ManagerLog();
        managerLog.setRemark(editLogRemark(byAdminName, remark));
        managerLog.setCreatedTime(DateUtil.now());
        managerLogRepository.save(managerLog);
    }

    /**
     * 编辑日志备注
     * @param adminUserName 管理员用户名
     * @param message 备注信息
     * @return 编辑后的备注
     */
    private String editLogRemark(String adminUserName, String message) {
        return adminUserName.concat(Constant.HALF_SPACE).concat(message);
    }
}
