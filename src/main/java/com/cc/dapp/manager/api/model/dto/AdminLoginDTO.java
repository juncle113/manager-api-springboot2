package com.cc.dapp.manager.api.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "AdminLoginDTO", description = "管理员登录信息")
@Data
@Validated
@Valid
public class AdminLoginDTO extends BaseDTO {

//    @ApiModelProperty(value = "用户名", required = true, example = "root")
    @NotBlank
    @Size(max = 2)
    private String userName;

//    @ApiModelProperty(value = "密码", required = true, example = "123456ctc")
    @NotBlank
    @Size(max = 2)
    private String password;
}
