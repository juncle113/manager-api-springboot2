package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import org.springframework.http.ResponseEntity;

public interface AdminService extends BaseService {

    public ResponseEntity<AdminLoginVO> login(AdminLoginDTO adminLoginDTO);

    public ResponseEntity logout();
}
