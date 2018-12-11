package com.cpt.dapp.manager.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息
 *
 * @author sunli
 * @date 2018/12/07
 */
@ApiModel(value = "UserDTO", description = "用户信息")
@Data
public class UserDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "user")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, example = "abcd1234")
    @NotBlank(message = "密码不能为空")
    private String password;
}
