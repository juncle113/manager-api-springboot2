package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "管理员")
@RequestMapping(value = "/admins")
@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录")
    @PostMapping("/token")
    public ResponseEntity<AdminLoginVO> getToken(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
        return ResponseEntity.ok(adminService.login(adminLoginDTO));
    }

//    @ApiOperation("查询管理员")
//    @GetMapping("/")
//    public ResponseEntity<List<AdminVO>> getList() {
//        return ResponseEntity.ok(adminService.getList());
//    }
//

    @ApiOperation("新建管理员")
    @PostMapping("/")
    public ResponseEntity<AdminVO> add(@RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.created(null).body(adminService.add(adminDTO));
    }

    @ApiOperation("修改管理员")
    @PutMapping("/{adminId}")
    public ResponseEntity<AdminVO> modify(@PathVariable(name = "adminId") Integer adminId,
                                          @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.modify(adminId, adminDTO));
    }

    @ApiOperation("删除管理员（逻辑删除）")
    @DeleteMapping("/{adminId}")
    public ResponseEntity remove(@PathVariable(name = "adminId") Integer adminId) {
        adminService.remove(adminId);
        return ResponseEntity.ok(null);
    }


//    @ApiOperation("注销")
//    @PostMapping("/logout")
//    public ResponseEntity logout() {
//        return adminService.logout();
//    }
}
