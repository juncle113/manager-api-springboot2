package com.cpt.dapp.manager.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 管理员登录信息
 *
 * @author sunli
 * @date 2018/12/07
 */
@ApiModel(value = "AdminLoginDTO", description = "管理员登录信息")
@Data
@Validated
@Valid
public class AdminLoginDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "root")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, example = "123456ctc")
    @NotBlank(message = "密码不能为空")
    private String password;
}
