package com.cc.dapp.manager.api.repository;

import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 管理员Repository
 *
 * @author sunli
 * @date 2018/12/07
 */
public interface ManagerAdminRepository extends JpaRepository<ManagerAdmin, Integer> {

    /**
     * 根据用户名查询管理员信息
     *
     * @param userName 用户名
     * @return 管理员信息
     */
    ManagerAdmin findByUserName(String userName);

    /**
     * 检查用户名是否存在
     *
     * @param userName 用户名
     * @return 是否存在
     */
    boolean existsByUserName(String userName);

}
