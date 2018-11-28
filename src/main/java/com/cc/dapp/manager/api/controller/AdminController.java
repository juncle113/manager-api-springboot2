package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.pojo.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.pojo.vo.AdminLoginVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "管理员")
@RequestMapping(value = "/admin")
@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<AdminLoginVO> login(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
        return ResponseEntity.ok(adminService.login(adminLoginDTO));
    }

//    @ApiOperation("登录")
//    @PostMapping("/login2")
//    public ResponseEntity<?> login2(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
////        throw new
//        return new ResponseEntity(new ErrorInfo(10001,"没有找到该用户"), HttpStatus.NOT_FOUND);
//    }

//    @ApiOperation("注销")
//    @PostMapping("/logout")
//    public ResponseEntity logout() {
//        return adminService.logout();
//    }
}
