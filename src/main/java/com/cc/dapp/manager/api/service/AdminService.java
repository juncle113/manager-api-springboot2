package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;

import java.util.List;

public interface AdminService extends BaseService {

    AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    void logout(Integer byAdminId);

    List<AdminVO> getList(Integer byAdminId);

    AdminVO add(Integer byAdminId, AdminDTO adminDTO);

    AdminVO modify(Integer byAdminId, Integer adminId, AdminDTO adminDTO);

    void remove(Integer byAdminId, Integer adminId);
}
