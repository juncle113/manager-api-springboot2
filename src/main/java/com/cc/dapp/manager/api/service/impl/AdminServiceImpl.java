package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.exception.LoginException;
import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        if (!adminLoginDTO.getUsername().equals("admin") ||
            !adminLoginDTO.getPassword().equals("abcd1234")) {
            throw new LoginException();
        }

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setName("admin");
        adminLoginVO.setUsername("管理员");

        return adminLoginVO;
    }

    @Override
    public AdminLoginVO logout() {
        return null;
    }
}
