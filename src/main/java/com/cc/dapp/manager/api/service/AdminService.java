package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.LoginVO;
import org.springframework.http.ResponseEntity;

public interface AdminService extends BaseService {

    public ResponseEntity<LoginVO> login(AdminLoginDTO adminLoginDTO);

    public ResponseEntity logout();
}
