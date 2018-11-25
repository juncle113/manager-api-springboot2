package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public ResponseEntity<AdminLoginVO> login(AdminLoginDTO adminLoginDTO) {

        if (!adminLoginDTO.getUsername().equals("admin") ||
            !adminLoginDTO.getPassword().equals("abcd1234")) {
//            throw new Exception();
            return new ResponseEntity("用户名或密码错误", HttpStatus.UNAUTHORIZED);
        }

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setName("admin");
        adminLoginVO.setUsername("管理员");

        return ResponseEntity.ok(adminLoginVO);
    }

    @Override
    public ResponseEntity logout() {
        return null;
    }
}
