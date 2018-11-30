package com.cc.dapp.manager.api.pojo.vo;

import com.cc.dapp.manager.api.enums.AdminRoleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AdminLoginVO", description = "登录VO")
@Data
public class AdminLoginVO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "姓名", required = true, example = "张三")
    private String name;

    @ApiModelProperty(value = "角色类型", required = true, example = "1|2")
    private Integer roleType;

    @ApiModelProperty(value = "角色类型名", required = true, example = "超级管理员|普通管理员")
    private String roleName;

    public String getRoleName() {
        return AdminRoleTypeEnum.getNameByCode(roleType);
    }

}
