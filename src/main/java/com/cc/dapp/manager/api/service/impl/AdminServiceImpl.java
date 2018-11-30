package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.constant.Constant;
import com.cc.dapp.manager.api.domain.ManagerAdmin;
import com.cc.dapp.manager.api.domain.ManagerLog;
import com.cc.dapp.manager.api.enums.AdminRemarkEnum;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AdminLoginException;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import com.cc.dapp.manager.api.repository.ManagerLogRepository;
import com.cc.dapp.manager.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Autowired
    private ManagerLogRepository managerLogRepository;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        ManagerLog managerLog = new ManagerLog();

        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());
        ManagerAdmin managerAdmin = managerAdminRepository.findByUserNameAndPassword(adminLoginDTO.getUsername(), passwordWithMD5);

        // 用户名或密码错误的场合
        if (managerAdmin == null) {

            managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_FAILED.getMessage()));
            managerLog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            managerLogRepository.save(managerLog);

            throw new AdminLoginException();
        }

        // 账号被禁用的场合
        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {

            managerLog.setId(managerAdmin.getId());
            managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_FORBIDDEN.getMessage()));
            managerLog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            managerLogRepository.save(managerLog);

            throw new AuthorizedException();
        }

        managerLog.setId(managerAdmin.getId());
        managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_SUCCESS.getMessage()));
        managerLog.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        managerLogRepository.save(managerLog);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setId(managerAdmin.getId());
        adminLoginVO.setToken("1");

        return adminLoginVO;
    }

    @Override
    public AdminLoginVO logout() {
        return null;
    }

    private String editRemark(String admin, String message) {
        return admin.concat(Constant.HALF_SPACE).concat(message);
    }
}
