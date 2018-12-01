package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.model.dto.UserDTO;
import com.cc.dapp.manager.api.model.vo.UserVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService extends BaseService {

    ResponseEntity<List<UserVO>> getUserList();

    ResponseEntity<UserVO> updateUser(UserDTO userDTO);

}
