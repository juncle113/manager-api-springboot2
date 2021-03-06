package com.cpt.dapp.manager.api.service.impl;

import com.cpt.dapp.manager.api.auth.AuthManager;
import com.cpt.dapp.manager.api.constant.ManagerLogConstant;
import com.cpt.dapp.manager.api.enums.AdminRoleEnum;
import com.cpt.dapp.manager.api.enums.AdminStatusEnum;
import com.cpt.dapp.manager.api.exception.AccountExistedException;
import com.cpt.dapp.manager.api.exception.AdminLoginException;
import com.cpt.dapp.manager.api.exception.AuthorizedException;
import com.cpt.dapp.manager.api.exception.DataNotFoundException;
import com.cpt.dapp.manager.api.pojo.domain.ManagerAdmin;
import com.cpt.dapp.manager.api.pojo.dto.AdminDTO;
import com.cpt.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cpt.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cpt.dapp.manager.api.pojo.vo.AdminVO;
import com.cpt.dapp.manager.api.repository.ManagerAdminRepository;
import com.cpt.dapp.manager.api.service.AdminService;
import com.cpt.dapp.manager.api.service.ManagerLogService;
import com.cpt.dapp.manager.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 管理员ServiceImpl
 *
 * @author sunli
 * @date 2018/12/07
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerAdminRepository managerAdminRepository;

    @Autowired
    private AuthManager authManager;

    @Autowired
    private ManagerLogService managerLogService;

    /**
     * 登录处理
     *
     * @param adminLoginDTO 管理员登录信息
     * @return 管理员登录信息
     */
    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {

        /* 1.根据用户名查询管理员信息 */
        ManagerAdmin managerAdmin = managerAdminRepository.findByUserName(adminLoginDTO.getUserName());

        /* 2.通过MD5加密登录密码 */
        String passwordWithMD5 = DigestUtils.md5DigestAsHex(adminLoginDTO.getPassword().getBytes());

        /* 3.进行登录检查 */
        // 登录失败：用户名或密码错误的场合
        if (managerAdmin == null || !Objects.equals(passwordWithMD5, managerAdmin.getPassword())) {
            managerLogService.log(adminLoginDTO.getUserName(), ManagerLogConstant.LOGIN_FAILED);
            throw new AdminLoginException();
        }

        // 登录失败：账号被禁用的场合
        if (AdminStatusEnum.INVALID.getCode() == managerAdmin.getStatus()) {
            managerLogService.log(managerAdmin.getId(), ManagerLogConstant.LOGIN_FORBIDDEN);
            throw new AuthorizedException();
        }

        /* 4.创建token，并缓存 */
        String token = authManager.createToken(String.valueOf(managerAdmin.getId()));

        /* 5.登录成功，返回登录信息（id、token） */
        managerLogService.log(managerAdmin.getId(), ManagerLogConstant.LOGIN_SUCCESS);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setId(managerAdmin.getId());
        adminLoginVO.setToken(token);

        return adminLoginVO;
    }

    /**
     * 注销处理
     *
     * @param byAdminId 当前管理员id
     */
    @Override
    public void logout(Integer byAdminId) {
        // 清除缓存中的token
        authManager.removeToken(String.valueOf(byAdminId));
    }

    /**
     * 根据id取得管理员信息
     * 取得未被逻辑删除的记录
     *
     * @param adminId 管理员id
     * @return 管理员信息
     */
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

    /**
     * 取得全部管理员信息
     * 取得未被逻辑删除的记录
     *
     * @return 管理员信息列表
     */
    @Override
    public List<AdminVO> getAll() {

        List<ManagerAdmin> managerAdmins = managerAdminRepository.findAll();

        List<AdminVO> adminVOs = new ArrayList<>();
        for (ManagerAdmin managerAdmin : managerAdmins) {
            adminVOs.add(editAdminVO(managerAdmin));
        }

        return adminVOs;
    }

    /**
     * 新增管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminDTO  新增的管理员信息
     * @return 新增的管理员信息
     */
    @Override
    public AdminVO add(Integer byAdminId, AdminDTO adminDTO) {

        /* 1.检查用户名是否被占用 */
        // 不包括已被逻辑删除的账号
        boolean isExist = managerAdminRepository.existsByUserName(adminDTO.getUserName());
        if (isExist) {
            throw new AccountExistedException();
        }

        /* 2.设置新增账号信息 */
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
        managerAdmin = managerAdminRepository.save(managerAdmin);

        managerLogService.log(byAdminId, ManagerLogConstant.ADD_ADMIN.concat(String.valueOf(managerAdmin.getId())));

        /* 3.返回新增的管理员信息 */
        AdminVO adminVO = editAdminVO(managerAdmin);

        return adminVO;
    }

    /**
     * 修改管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminId   被修改的管理员id
     * @param adminDTO  修改的管理员信息
     * @return 修改后的管理员信息
     */
    @Override
    public AdminVO modify(Integer byAdminId, Integer adminId, AdminDTO adminDTO) {

        /* 1.取得被修改账号信息 */
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        /* 2.检查账号角色（无权处理root） */
        ManagerAdmin managerAdmin = managerAdminOptional.get();
        checkoutAdminRole(managerAdmin.getRoleType());

        /* 3.设置修改内容 */
        managerAdmin.setName(adminDTO.getName());
        managerAdmin.setPassword(DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes()));
        managerAdmin.setRoleType(adminDTO.getRoleType());
        managerAdmin.setRemark(adminDTO.getRemark());
        managerAdmin.setStatus(adminDTO.getStatus());
        managerAdmin.setModifiedTime(DateUtil.now());
        managerAdmin.setModifiedById(byAdminId);
        managerAdmin = managerAdminRepository.save(managerAdmin);

        managerLogService.log(byAdminId, ManagerLogConstant.MODIFY_ADMIN.concat(String.valueOf(managerAdmin.getId())));

        /* 4.返回修改后的内容 */
        AdminVO adminVO = editAdminVO(managerAdmin);

        return adminVO;
    }

    /**
     * 删除管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminId   被删除的管理员id
     */
    @Override
    public void remove(Integer byAdminId, Integer adminId) {

        /* 1.取得被删除账号信息 */
        Optional<ManagerAdmin> managerAdminOptional = managerAdminRepository.findById(adminId);
        if (!managerAdminOptional.isPresent()) {
            throw new DataNotFoundException();
        }

        /* 2.检查账号角色（无权处理root） */
        ManagerAdmin managerAdmin = managerAdminOptional.get();
        checkoutAdminRole(managerAdmin.getRoleType());

        /* 3.删除账号 */
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

        // 查询创建和修改人信息
        String createdByUserName = managerAdminRepository.findUserNameByIdAndDeleted(managerAdmin.getCreatedById(), true);
        String modifiedByUserName = managerAdminRepository.findUserNameByIdAndDeleted(managerAdmin.getModifiedById(), true);

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
        adminVO.setCreatedByUserName(createdByUserName);
        adminVO.setModifiedTime(managerAdmin.getModifiedTime());
        adminVO.setModifiedBy(managerAdmin.getModifiedById());
        adminVO.setModifiedByUserName(modifiedByUserName);

        return adminVO;
    }

    /**
     * 检查账号角色
     * 不能处理root账号
     *
     * @param roleType 账号角色
     * @throws AuthorizedException 权限异常
     */
    private void checkoutAdminRole(Integer roleType) {
        // 处理root账号的场合，提示无权限
        if (roleType == AdminRoleEnum.ROOT.getCode()) {
            throw new AuthorizedException();
        }
    }
}
