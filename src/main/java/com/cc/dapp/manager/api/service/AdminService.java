package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;

public interface AdminService extends BaseService {

    public AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    public AdminLoginVO logout();
}
