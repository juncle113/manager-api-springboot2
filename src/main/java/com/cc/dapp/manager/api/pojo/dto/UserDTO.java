package com.cc.dapp.manager.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "UserDTO", description = "用户DTO")
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "user")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, example = "abcd1234")
    @NotBlank(message = "密码不能为空")
    private String password;
}
