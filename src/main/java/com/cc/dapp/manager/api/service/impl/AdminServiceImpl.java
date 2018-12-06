package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.auth.AuthManager;
import com.cc.dapp.manager.api.constant.ManagerLogConstant;
import com.cc.dapp.manager.api.enums.AdminRoleEnum;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AccountExistedException;
import com.cc.dapp.manager.api.exception.AdminLoginException;
import com.cc.dapp.manager.api.exception.AuthorizedException;
import com.cc.dapp.manager.api.exception.DataNotFoundException;
import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.repository.ManagerAdminRepository;
import com.cc.dapp.manager.api.service.AdminService;
import com.cc.dapp.manager.api.service.ManagerLogService;
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
    private AuthManager authManager;

    @Autowired
    private ManagerLogService managerLogService;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        ManagerAdmin managerAdmin = managerAdminRepository.findByUserName(adminLoginDTO.getUserName());
        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());

        // 登录失败：用户名或密码错误的场合
        if (managerAdmin == null || !passwordWithMD5.equals(managerAdmin.getPassword())) {
            managerLogService.log(adminLoginDTO.getUserName(), ManagerLogConstant.LOGIN_FAILED);
            throw new AdminLoginException();
        }

        // 登录失败：账号被禁用的场合
        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {
            managerLogService.log(managerAdmin.getId(), ManagerLogConstant.LOGIN_FORBIDDEN);
            throw new AuthorizedException();
        }

        // 登录成功的场合
        managerLogService.log(managerAdmin.getId(), ManagerLogConstant.LOGIN_SUCCESS);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setId(managerAdmin.getId());
        adminLoginVO.setToken(authManager.createToken(String.valueOf(managerAdmin.getId())));

        return adminLoginVO;
    }

    @Override
    public void logout(Integer byAdminId) {
        authManager.removeToken(String.valueOf(byAdminId));
    }

    @Override
    public AdminVO getById(Integer adminId) {

        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        AdminVO adminVO = editAdminVO(managerAdmin);

        return adminVO;
    }

    @Override
    public List<AdminVO> search() {

        List<ManagerAdmin> managerAdmins = managerAdminRepository.findAll();

        List<AdminVO> adminVOs = new ArrayList<>();
        for (ManagerAdmin managerAdmin : managerAdmins) {
            adminVOs.add(editAdminVO(managerAdmin));
        }

        return adminVOs;
    }

    @Override
    public AdminVO add(Integer byAdminId, AdminDTO adminDTO) {

        // 检查用户名是否被占用（不包括已被逻辑删除的账号）
        int count = managerAdminRepository.countByUserName(adminDTO.getUserName());
        if (count > 0) {
            throw new AccountExistedException();
        }

        // 创建账号
        ManagerAdmin managerAdmin = new ManagerAdmin();
        managerAdmin.setUserName(adminDTO.getUserName());
        managerAdmin.setName(adminDTO.getName());
        managerAdmin.setPassword(DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes()));
        managerAdmin.setRoleType(adminDTO.getRoleType());
        managerAdmin.setRemark(adminDTO.getRemark());
        managerAdmin.setStatus(adminDTO.getStatus());
        managerAdmin.setDeleted(false);
        managerAdmin.setCreatedTime(DateUtil.now());
        managerAdmin.setCreatedById(byAdminId);
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(byAdminId);
        AdminVO adminVO = editAdminVO(managerAdminRepository.save(managerAdmin));

        managerLogService.log(byAdminId, ManagerLogConstant.ADD_ADMIN.concat(String.valueOf(adminVO.getId())));

        return adminVO;
    }

    @Override
    public AdminVO modify(Integer byAdminId, Integer adminId, AdminDTO adminDTO) {

        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (managerAdmin.getRoleType() == AdminRoleEnum.ROOT.getCode()) {
            // 不能修改root账号
            throw new AuthorizedException();
        }

        managerAdmin.setName(adminDTO.getName());
        managerAdmin.setPassword(DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes()));
        managerAdmin.setRoleType(adminDTO.getRoleType());
        managerAdmin.setRemark(adminDTO.getRemark());
        managerAdmin.setStatus(adminDTO.getStatus());
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(byAdminId);
        AdminVO adminVO = editAdminVO(managerAdminRepository.save(managerAdmin));

        managerLogService.log(byAdminId, ManagerLogConstant.MODIFY_ADMIN.concat(String.valueOf(adminVO.getId())));

        return adminVO;
    }

    @Override
    public void remove(Integer byAdminId, Integer adminId) {

        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (managerAdmin.getRoleType() == AdminRoleEnum.ROOT.getCode()) {
            // 不能删除root账号
            throw new AuthorizedException();
        }

        managerAdminRepository.deleteById(adminId);

        managerLogService.log(byAdminId, ManagerLogConstant.REMOVE_ADMIN.concat(String.valueOf(adminId)));
    }

    /**
     * 编辑管理员VO
     *
     * @param managerAdmin 管理员信息
     * @return 编辑后的管理员VO
     */
    private AdminVO editAdminVO(ManagerAdmin managerAdmin) {
        AdminVO adminVO = new AdminVO();
        adminVO.setId(managerAdmin.getId());
        adminVO.setUserName(managerAdmin.getUserName());
        adminVO.setName(managerAdmin.getName());
        adminVO.setRoleType(managerAdmin.getRoleType());
        adminVO.setRoleTypeName(AdminRoleEnum.getNameByCode(managerAdmin.getRoleType()));
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
