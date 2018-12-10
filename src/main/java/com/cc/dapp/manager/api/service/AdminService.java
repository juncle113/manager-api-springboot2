package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;

import java.util.List;

/**
 * 管理员Service
 *
 * @author sunli
 * @date 2018/12/07
 */
public interface AdminService extends BaseService {

    /**
     * 登录处理
     *
     * @param adminLoginDTO 管理员登录信息
     * @return 管理员登录信息
     */
    AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    /**
     * 注销处理
     *
     * @param byAdminId 当前管理员id
     */
    void logout(Integer byAdminId);

    /**
     * 根据id取得管理员信息
     *
     * @param adminId 管理员id
     * @return 管理员信息
     */
    AdminVO getById(Integer adminId);

    /**
     * 取得全部管理员信息
     *
     * @return 管理员信息列表
     */
    List<AdminVO> getAll();

    /**
     * 新增管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminDTO  新增的管理员信息
     * @return 新增的管理员信息
     */
    AdminVO add(Integer byAdminId, AdminDTO adminDTO);

    /**
     * 修改管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminId   被修改的管理员id
     * @param adminDTO  修改的管理员信息
     * @return 修改后的管理员信息
     */
    AdminVO modify(Integer byAdminId, Integer adminId, AdminDTO adminDTO);

    /**
     * 删除管理员
     *
     * @param byAdminId 当前管理员id
     * @param adminId   被删除的管理员id
     */
    void remove(Integer byAdminId, Integer adminId);
}
