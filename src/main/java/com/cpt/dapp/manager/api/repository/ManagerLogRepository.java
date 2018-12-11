package com.cpt.dapp.manager.api.repository;

import com.cpt.dapp.manager.api.model.domain.ManagerLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 管理日志Repository
 *
 * @author sunli
 * @date 2018/12/07
 */
public interface ManagerLogRepository extends JpaRepository<ManagerLog, Integer> {

}
