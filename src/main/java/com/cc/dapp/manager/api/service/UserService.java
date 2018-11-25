package com.cc.dapp.manager.api.service;

import com.cc.dapp.manager.api.pojo.dto.UserDTO;
import com.cc.dapp.manager.api.pojo.vo.UserVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService extends BaseService {

    ResponseEntity<List<UserVO>> getUserList();

    ResponseEntity<UserVO> updateUser(UserDTO userDTO);

}
