package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.constant.Constant;
import com.cc.dapp.manager.api.enums.AdminRemarkEnum;
import com.cc.dapp.manager.api.enums.AdminRoleTypeEnum;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AccountHasExistedException;
import com.cc.dapp.manager.api.exception.AdminLoginException;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.exception.DataNotFoundException;
import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import com.cc.dapp.manager.api.model.domain.ManagerLog;
import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import com.cc.dapp.manager.api.repository.ManagerLogRepository;
import com.cc.dapp.manager.api.service.AdminService;
import com.cc.dapp.manager.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Autowired
    private ManagerLogRepository managerLogRepository;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        ManagerLog managerLog = new ManagerLog();

        ManagerAdmin managerAdmin = managerAdminRepository.findByUserNameAndDeleted(adminLoginDTO.getUserName(), false);
        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());

        // 登录失败：用户名或密码错误的场合
        if (managerAdmin == null || !passwordWithMD5.equals(managerAdmin.getPassword())) {

            managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_FAILED.getMessage()));
            managerLog.setCreatedTime(DateUtil.now());
            managerLogRepository.save(managerLog);

            throw new AdminLoginException();
        }

        // 登录失败：账号被禁用的场合
        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {

            managerLog.setId(managerAdmin.getId());
            managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_FORBIDDEN.getMessage()));
            managerLog.setCreatedTime(DateUtil.now());
            managerLogRepository.save(managerLog);

            throw new AuthorizedException();
        }

        // 登录成功的场合
        managerLog.setId(managerAdmin.getId());
        managerLog.setRemark(editRemark(managerAdmin.getUserName(), AdminRemarkEnum.LOGIN_SUCCESS.getMessage()));
        managerLog.setCreatedTime(DateUtil.now());
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

    @Override
    public List<AdminVO> getList() {

        List<ManagerAdmin> managerAdmins = managerAdminRepository.findAll();

        List<AdminVO> adminVOs = new ArrayList<>();
        for (ManagerAdmin managerAdmin : managerAdmins) {
            adminVOs.add(editAdminVO(managerAdmin));
        }

        return adminVOs;
    }

    @Override
    public AdminVO add(AdminDTO adminDTO) {

        int count = managerAdminRepository.countByUserName(adminDTO.getUserName());
        if (count > 0) {
            throw new AccountHasExistedException();
        }

        ManagerAdmin managerAdmin = new ManagerAdmin();
        managerAdmin.setUserName(adminDTO.getUserName());
        managerAdmin.setName(adminDTO.getName());
        managerAdmin.setPassword(DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes()));
        managerAdmin.setRoleType(adminDTO.getRoleType());
        managerAdmin.setRemark(adminDTO.getRemark());
        managerAdmin.setStatus(adminDTO.getStatus());
        managerAdmin.setDeleted(false);
        managerAdmin.setCreatedTime(DateUtil.now());
        managerAdmin.setCreatedById(adminDTO.getByAdminId());
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(adminDTO.getByAdminId());

        return editAdminVO(managerAdminRepository.save(managerAdmin));
    }

    @Override
    public AdminVO modify(Integer adminId, AdminDTO adminDTO) {
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (managerAdmin.getRoleType() == AdminRoleTypeEnum.ROOT.getCode()) {
            // 不能修改root账号
            throw new AuthorizedException();
        }

        managerAdmin.setName(adminDTO.getName());
        managerAdmin.setPassword(DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes()));
        managerAdmin.setRoleType(adminDTO.getRoleType());
        managerAdmin.setRemark(adminDTO.getRemark());
        managerAdmin.setStatus(adminDTO.getStatus());
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(adminDTO.getByAdminId());

        return editAdminVO(managerAdminRepository.save(managerAdmin));
    }

    @Override
    public void remove(Integer adminId) {


        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (managerAdmin.getRoleType() == AdminRoleTypeEnum.ROOT.getCode()) {
            // 不能删除root账号
            throw new AuthorizedException();
        }

        managerAdminRepository.deleteById(adminId);
    }

    private String editRemark(String adminUserName, String message) {
        return adminUserName.concat(Constant.HALF_SPACE).concat(message);
    }

    private AdminVO editAdminVO(ManagerAdmin managerAdmin) {
        AdminVO adminVO = new AdminVO();
        adminVO.setId(managerAdmin.getId());
        adminVO.setUserName(managerAdmin.getUserName());
        adminVO.setName(managerAdmin.getName());
        adminVO.setRoleType(managerAdmin.getRoleType());
        adminVO.setRoleTypeName(AdminRoleTypeEnum.getNameByCode(managerAdmin.getRoleType()));
        adminVO.setRemark(managerAdmin.getRemark());
        adminVO.setStatus(managerAdmin.getStatus());
        adminVO.setStatusName(AdminStatusEnum.getNameByCode(managerAdmin.getStatus()));
        adminVO.setCreatedTime(managerAdmin.getCreatedTime());
        adminVO.setCreatedBy(managerAdmin.getCreatedById());
        adminVO.setCreatedByUserName("manytoone");
        adminVO.setModifiedTime(managerAdmin.getModifiedTime());
        adminVO.setModifiedBy(managerAdmin.getModifiedById());
        adminVO.setModifiedByUserName("manytoone2");

        return adminVO;
    }
}
