package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.pojo.dto.UserDTO;
import com.cc.dapp.manager.api.pojo.vo.UserVO;
import com.cc.dapp.manager.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "用户")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("取得用户列表")
    @GetMapping("/users")
    public ResponseEntity<List<UserVO>> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation("修改用户")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserVO> upateUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }
}
