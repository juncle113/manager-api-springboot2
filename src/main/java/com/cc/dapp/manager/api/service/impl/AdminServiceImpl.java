package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.domain.ManagerAdmin;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AdminLoginException;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import com.cc.dapp.manager.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());
        ManagerAdmin managerAdmin = managerAdminRepository.findByUserNameAndPassword(adminLoginDTO.getUsername(), passwordWithMD5);
        if (managerAdmin == null) {
            throw new AdminLoginException();
        }

        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {
            throw new AuthorizedException();
        }

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setName(managerAdmin.getName());
        adminLoginVO.setUsername(managerAdmin.getUserName());
        adminLoginVO.setRoleType(managerAdmin.getRoleType());

        return adminLoginVO;
    }

    @Override
    public AdminLoginVO logout() {
        return null;
    }
}
