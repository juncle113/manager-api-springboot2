package com.cc.dapp.manager.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "AdminLoginDTO", description = "管理员登录DTO")
@Data
public class AdminLoginDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "abcd1234")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "返回密码的MD5格式", hidden = true)
    public String getPasswordWithMD5() {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
