package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.annotation.Auth;
import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.*;
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

    @ApiOperation(value = "登录", notes = "通过检查管理员的用户名和密码，完成登录")
//    @PostMapping("/oauth2/token")
    @PostMapping("/token")

    public ResponseEntity<AdminLoginVO> login(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
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
    @Auth
    public ResponseEntity<AdminVO> add(@RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.created(null).body(adminService.add(adminDTO));
    }

    @ApiOperation("修改管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "adminId", paramType = "query", defaultValue = "2", required = true)
    })
    @PutMapping("/{adminId}")
    @Auth
    public ResponseEntity<AdminVO> modify(@PathVariable(name = "adminId") Integer adminId,
                                          @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.modify(adminId, adminDTO));
    }

    @ApiOperation("删除管理员（逻辑删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "byAdminId", value = "byAdminId", paramType = "query", defaultValue = "1", required = true)
    })
    @DeleteMapping("/{adminId}")
    @Auth
    public ResponseEntity remove(@PathVariable(name = "adminId") Integer adminId,
                                 @RequestParam(name = "byAdminId") Integer byAdminId) {
        adminService.remove(adminId, byAdminId);
        return ResponseEntity.ok(null);
    }

//    @ApiOperation("注销")
//    @DeleteMapping("/token")
//    public ResponseEntity logout() {
//        return adminService.logout();
//    }
}
