package com.cc.dapp.manager.api.service.impl;

import com.cc.dapp.manager.api.model.dto.UserDTO;
import com.cc.dapp.manager.api.model.vo.UserVO;
import com.cc.dapp.manager.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<List<UserVO>> getUserList() {

        List<UserVO> userVOList = new ArrayList<>();
        UserVO userVO = new UserVO();
        userVO.setName("用户01");
        userVOList.add(userVO);

        userVO = new UserVO();
        userVO.setName("用户02");
        userVOList.add(userVO);

        userVO = new UserVO();
        userVO.setName("用户03");
        userVOList.add(userVO);

        return ResponseEntity.ok(userVOList);
    }

    @Override
    public ResponseEntity<UserVO> updateUser(UserDTO userDTO) {

        UserVO userVO = new UserVO();
        userVO.setUsername("user");
        userVO.setName("用户01");

        return ResponseEntity.ok(userVO);
    }

}
