package com.cc.dapp.manager.api.repository;

import com.cc.dapp.manager.api.model.domain.ManagerAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerAdminRepository extends JpaRepository<ManagerAdmin, Integer> {

    ManagerAdmin findByUserNameAndDeleted(String userName, Boolean deleted);

    ManagerAdmin findByUserName(String userName);

    int countByUserName(String userName);



}
