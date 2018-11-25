package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "管理员")
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录")
    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AdminLoginVO> login(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
        return adminService.login(adminLoginDTO);
    }

    @ApiOperation("注销")
    @PostMapping("/logout")
    public ResponseEntity logout() {
        return adminService.logout();
    }
}
