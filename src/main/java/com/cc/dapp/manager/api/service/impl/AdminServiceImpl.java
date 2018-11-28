package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.exception.AuthorizedException;
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
//            throw new AuthorizedException(new ErrorInfo(10001, "权限问题"));
//            throw new Exception("10001");
            throw new AuthorizedException();


//            throw new BusinessException(ErrorCodeEnum.ADMIN_LOGIN_ERROR);
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
