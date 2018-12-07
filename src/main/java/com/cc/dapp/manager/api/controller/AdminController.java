package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.auth.AuthManager;
import com.cc.dapp.manager.api.auth.annotation.Auth;
import com.cc.dapp.manager.api.auth.annotation.CurrentId;
import com.cc.dapp.manager.api.model.dto.AdminDTO;
import com.cc.dapp.manager.api.model.dto.AdminLoginDTO;
import com.cc.dapp.manager.api.model.vo.AdminLoginVO;
import com.cc.dapp.manager.api.model.vo.AdminVO;
import com.cc.dapp.manager.api.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "管理员")
@RequestMapping(value = "/admins")
@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录", notes = "通过检查管理员的用户名和密码，完成登录。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminLoginDTO", value = "管理员登录信息", paramType = "body", dataType = "AdminLoginDTO", required = true)
    })
    @PostMapping("/token")
    public ResponseEntity<AdminLoginVO> login(@RequestBody @Valid AdminLoginDTO adminLoginDTO) {
        return ResponseEntity.created(null).body(adminService.login(adminLoginDTO));
    }

    @ApiOperation(value = "注销", notes = "清除缓存中当前登录账号的token。")
    @DeleteMapping("/self/token")
    @Auth(AuthManager.READ)
    public ResponseEntity logout(@ApiParam(hidden = true) @CurrentId Integer byAdminId) {
        adminService.logout(byAdminId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "取得管理员", notes = "根据管理员id，取得管理员信息。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", dataType = "int", paramType = "path", defaultValue = "2", required = true)
    })
    @GetMapping("/{adminId}")
    @Auth(AuthManager.READ)
    public ResponseEntity<AdminVO> getById(@PathVariable @NotNull(message = "管理员id不能为空") Integer adminId) {
        return ResponseEntity.ok(adminService.getById(adminId));
    }

    @ApiOperation(value = "取得管理员列表", notes = "取得全部管理员信息。")
    @GetMapping("")
    @Auth(AuthManager.READ)
    public ResponseEntity<List<AdminVO>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @ApiOperation(value = "新建管理员", notes = "使用root账户创建管理员。")
    @PostMapping
    @Auth
    @Transactional
    public ResponseEntity<AdminVO> add(@ApiParam(hidden = true) @CurrentId Integer byAdminId,
                                       @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.created(null).body(adminService.add(byAdminId, adminDTO));
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员信息，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", paramType = "path", defaultValue = "2", required = true)
    })
    @PutMapping("/{adminId}")
    @Auth
    @Transactional
    public ResponseEntity<AdminVO> modify(@ApiParam(hidden = true) @CurrentId Integer byAdminId,
                                          @PathVariable @NotNull(message = "管理员id不能为空") Integer adminId,
                                          @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.modify(byAdminId, adminId, adminDTO));
    }

    @ApiOperation(value = "删除管理员", notes = "逻辑删除管理员，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", dataType = "int", paramType = "path", defaultValue = "2", required = true)
    })
    @DeleteMapping("/{adminId}")
    @Auth
    @Transactional
    public ResponseEntity remove(@ApiParam(hidden = true) @CurrentId Integer byAdminId,
                                 @PathVariable Integer adminId) {
        adminService.remove(byAdminId, adminId);
        return ResponseEntity.noContent().build();
    }
}
