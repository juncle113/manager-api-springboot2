package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.pojo.dto.AdminDTO;
import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.pojo.vo.AdminVO;

import java.util.List;

public interface AdminService extends BaseService {

    AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    AdminLoginVO logout();

    List<AdminVO> getList();

    AdminVO add(AdminDTO adminDTO);

    AdminVO modify(Integer adminId, AdminDTO adminDTO);
}
