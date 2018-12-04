package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.annotation.Auth;
import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiOperation(value = "登录", notes = "通过检查管理员的用户名和密码，完成登录")
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

    @ApiOperation(value = "新建管理员", notes = "使用root账户创建管理员")
    @PostMapping("/")
    @Auth
    public ResponseEntity<AdminVO> add(@RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.created(null).body(adminService.add(adminDTO));
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员信息，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", paramType = "path", defaultValue = "2", required = true)
    })
    @PutMapping("/{adminId}")
    @Auth
    public ResponseEntity<AdminVO> modify(@PathVariable Integer adminId,
                                          @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.modify(adminId, adminDTO));
    }

    @ApiOperation(value = "删除管理员", notes = "逻辑删除管理员，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", paramType = "path", defaultValue = "2", required = true),
            @ApiImplicitParam(name = "byAdminId", value = "操作人id", paramType = "query", defaultValue = "1", required = true)
    })
    @DeleteMapping("/{adminId}")
    @Auth
    public ResponseEntity remove(@PathVariable Integer adminId,
                                 @RequestParam Integer byAdminId) {
        adminService.remove(adminId, byAdminId);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "注销", notes = "清除缓存的token")
    @DeleteMapping("/{adminId}/token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", paramType = "path", defaultValue = "1", required = true)
    })
    public ResponseEntity logout(@PathVariable Integer adminId) {
        adminService.logout(adminId);
        return ResponseEntity.ok(null);
    }
}
