package com.cc.dapp.manager.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "LoginVO", description = "登录VO")
@Data
public class UserVO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "姓名", required = true, example = "管理员")
    private String name;

}
