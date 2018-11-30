package com.cc.dapp.manager.api.repository;

import com.cc.dapp.manager.api.domain.ManagerAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerAdminRepository extends JpaRepository<ManagerAdmin, Integer> {

    ManagerAdmin findByUserNameAndPassword(String userName, String password);



}
