package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.auth.AuthManager;
import com.cc.dapp.manager.api.auth.annotation.Auth;
import com.cc.dapp.manager.api.auth.annotation.CurrentId;
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
        return ResponseEntity.ok(adminService.login(adminLoginDTO));
    }

    @ApiOperation(value = "注销", notes = "清除缓存中当前登录账号的token。")
    @DeleteMapping("/self/token")
    @Auth
    public ResponseEntity logout(@CurrentId Integer byAdminId) {
        adminService.logout(byAdminId);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "取得管理员", notes = "根据管理员id，取得管理员信息。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", dataType = "int", paramType = "path", defaultValue = "2", required = true)
    })
    @GetMapping("/{adminId}")
    @Auth(AuthManager.READ)
    public ResponseEntity<AdminVO> getById(@PathVariable Integer adminId) {
        return ResponseEntity.ok(adminService.getById(adminId));
    }

    @ApiOperation(value = "查询管理员", notes = "查询符合条件的管理员信息。")
    @ApiImplicitParams({

    })
    @GetMapping("/")
    @Auth(AuthManager.READ)
    public ResponseEntity<List<AdminVO>> search() {
        // TODO 设置查询条件
        return ResponseEntity.ok(adminService.search());
    }

    @ApiOperation(value = "新建管理员", notes = "使用root账户创建管理员。")
    @PostMapping
    @Auth
    public ResponseEntity<AdminVO> add(@CurrentId Integer byAdminId,
                                       @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.created(null).body(adminService.add(byAdminId, adminDTO));
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员信息，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", paramType = "path", defaultValue = "2", required = true)
    })
    @PutMapping("/{adminId}")
    @Auth
    public ResponseEntity<AdminVO> modify(@CurrentId Integer byAdminId,
                                          @PathVariable Integer adminId,
                                          @RequestBody @Valid AdminDTO adminDTO) {
        return ResponseEntity.ok(adminService.modify(byAdminId, adminId, adminDTO));
    }

    @ApiOperation(value = "删除管理员", notes = "逻辑删除管理员，不能操作root账户。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员id", dataType = "int", paramType = "path", defaultValue = "2", required = true)
    })
    @DeleteMapping("/{adminId}")
    @Auth
    public ResponseEntity remove(@CurrentId Integer byAdminId,
                                 @PathVariable Integer adminId) {
        adminService.remove(byAdminId, adminId);
        return ResponseEntity.ok(null);
    }
}
