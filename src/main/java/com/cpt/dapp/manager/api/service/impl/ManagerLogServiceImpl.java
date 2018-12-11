package com.cpt.dapp.manager.api.service.impl;

import com.cpt.dapp.manager.api.constant.Constant;
import com.cpt.dapp.manager.api.exception.DataNotFoundException;
import com.cpt.dapp.manager.api.model.domain.ManagerAdmin;
import com.cpt.dapp.manager.api.model.domain.ManagerLog;
import com.cpt.dapp.manager.api.repository.ManagerAdminRepository;
import com.cpt.dapp.manager.api.repository.ManagerLogRepository;
import com.cpt.dapp.manager.api.service.ManagerLogService;
import com.cpt.dapp.manager.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 管理日志ServiceImpl
 *
 * @author sunli
 * @date 2018/12/07
 */
@Service
public class ManagerLogServiceImpl implements ManagerLogService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Autowired
    private ManagerLogRepository managerLogRepository;

    /**
     * 保存日志（记录管理员id）
     *
     * @param byAdminId 当前管理员id
     * @param remark    操作备注
     */
    @Override
    public void log(Integer byAdminId, String remark) {

        // 根据当前管理员id查询管理员用户名
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(byAdminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        ManagerLog managerLog = new ManagerLog();
        managerLog.setRemark(editLogRemark(managerAdmin.getUserName(), remark));
        managerLog.setCreatedTime(DateUtil.now());
        managerLog.setCreatedById(managerAdmin.getId());
        managerLogRepository.save(managerLog);
    }

    /**
     * 保存日志（未取得管理员id的场合，记录登录时用户名）
     *
     * @param byAdminName 当前管理员用户名
     * @param remark      操作备注
     */
    @Override
    public void log(String byAdminName, String remark) {
        ManagerLog managerLog = new ManagerLog();
        managerLog.setRemark(editLogRemark(byAdminName, remark));
        managerLog.setCreatedTime(DateUtil.now());
        managerLogRepository.save(managerLog);
    }

    /**
     * 编辑日志备注
     *
     * @param adminUserName 管理员用户名
     * @param message       备注信息
     * @return 编辑后的备注
     */
    private String editLogRemark(String adminUserName, String message) {
        return adminUserName.concat(Constant.HALF_SPACE).concat(message);
    }
}
