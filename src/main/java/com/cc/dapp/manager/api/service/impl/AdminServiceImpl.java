package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.constant.AdminRemarkConstant;
import com.cc.dapp.manager.api.constant.Constant;
import com.cc.dapp.manager.api.enums.AdminRoleEnum;
import com.cc.dapp.manager.api.enums.AdminStatusEnum;
import com.cc.dapp.manager.api.exception.AccountExistedException;
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
import com.cc.dapp.manager.api.util.AuthUtil;
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

    @Autowired
    private AuthUtil authUtil;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        ManagerLog managerLog = new ManagerLog();

        ManagerAdmin managerAdmin = managerAdminRepository.findByUserName(adminLoginDTO.getUserName());
        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());

        // 登录失败：用户名或密码错误的场合
        if (managerAdmin == null || !passwordWithMD5.equals(managerAdmin.getPassword())) {

            managerLog.setRemark(editLogRemark(adminLoginDTO.getUserName(), AdminRemarkConstant.LOGIN_FAILED));
            managerLog.setCreatedTime(DateUtil.now());
            managerLogRepository.save(managerLog);

            throw new AdminLoginException();
        }

        // 登录失败：账号被禁用的场合
        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {

            managerLog.setId(managerAdmin.getId());
            managerLog.setRemark(editLogRemark(managerAdmin.getUserName(), AdminRemarkConstant.LOGIN_FORBIDDEN));
            managerLog.setCreatedTime(DateUtil.now());
            managerLogRepository.save(managerLog);

            throw new AuthorizedException();
        }

        // 登录成功的场合
        managerLog.setId(managerAdmin.getId());
        managerLog.setRemark(editLogRemark(managerAdmin.getUserName(), AdminRemarkConstant.LOGIN_SUCCESS));
        managerLog.setCreatedTime(DateUtil.now());
        managerLogRepository.save(managerLog);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setId(managerAdmin.getId());
//        adminLoginVO.setTokenType(AuthUtil.TOKEN_TYPE);
        adminLoginVO.setToken(AuthUtil.generateToken());

        authUtil.putToken("","");

        return adminLoginVO;
    }

    @Override
    public AdminLoginVO logout(Integer byAdminId) {
        return null;
    }

    @Override
    public List<AdminVO> getList(Integer byAdminId) {

        // 检查权限
        checkPermission(byAdminId, AuthUtil.READ_ONLY);

        List<ManagerAdmin> managerAdmins = managerAdminRepository.findAll();

        List<AdminVO> adminVOs = new ArrayList<>();
        for (ManagerAdmin managerAdmin : managerAdmins) {
            adminVOs.add(editAdminVO(managerAdmin));
        }

        return adminVOs;
    }

    @Override
    public AdminVO add(AdminDTO adminDTO) {

        // 检查权限
        checkPermission(adminDTO.getByAdminId(), AuthUtil.WRITE);

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
        managerAdmin.setCreatedById(adminDTO.getByAdminId());
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(adminDTO.getByAdminId());

        return editAdminVO(managerAdminRepository.save(managerAdmin));
    }

    @Override
    public AdminVO modify(Integer adminId, AdminDTO adminDTO) {

        // 检查权限
        checkPermission(adminDTO.getByAdminId(), AuthUtil.WRITE);

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
        managerAdmin.setModifiedById(adminDTO.getByAdminId());

        return editAdminVO(managerAdminRepository.save(managerAdmin));
    }

    @Override
    public void remove(Integer adminId, Integer byAdminId) {

        // 检查权限
        checkPermission(byAdminId, AuthUtil.WRITE);

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
    }

    /**
     * 编辑日志备注
     * @param adminUserName 管理员用户名
     * @param message 备注信息
     * @return 编辑后的备注
     */
    private String editLogRemark(String adminUserName, String message) {
        return adminUserName.concat(Constant.HALF_SPACE).concat(message);
    }

    /**
     * 编辑管理员VO
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

    /**
     * 检查权限
     * @param byAdminId 管理员id
     * @param authType 检查权限类型
     * @throws DataNotFoundException 数据不存在
     * @throws AuthorizedException 无访问权限
     */
    private void checkPermission(Integer byAdminId, String authType) {

        // 检查账号是否存在
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(byAdminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        // 检查是否被禁用
        ManagerAdmin managerAdmin = managerAdminOptional.get();
        if (AdminStatusEnum.VALID.equals(managerAdmin.getStatus())) {
            throw new AuthorizedException();
        }

        // 检查可写权限（有权：系统管理员、超级管理员，无权：普通管理员）
        if (AuthUtil.WRITE.equals(authType)) {
            if (managerAdmin.getRoleType() != AdminRoleEnum.ROOT.getCode() &&
                    managerAdmin.getRoleType() != AdminRoleEnum.SUPER_ADMIN.getCode()) {
                throw new AuthorizedException();
            }
        }
    }
}
