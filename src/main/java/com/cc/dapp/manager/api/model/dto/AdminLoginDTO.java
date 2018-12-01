package com.cc.dapp.manager.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "AdminLoginDTO", description = "管理员登录DTO")
@Data
public class AdminLoginDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "root")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, example = "123456ctc")
    @NotBlank(message = "密码不能为空")
    private String password;
}
