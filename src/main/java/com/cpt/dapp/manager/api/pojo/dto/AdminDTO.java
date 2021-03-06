package com.cpt.dapp.manager.api.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 管理员信息
 *
 * @author sunli
 * @date 2018/12/07
 */
@ApiModel(value = "AdminDTO", description = "管理员信息")
@Data
public class AdminDTO extends BaseDTO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{4,16}$", message = "用户名必须为4-16位字母或数字组合")
    private String userName;

    @ApiModelProperty(value = "密码", required = true, example = "abcd1234")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{8,16}$", message = "密码必须为8-16位字母或数字组合")
    private String password;

    @ApiModelProperty(value = "姓名", required = true, example = "李四")
    @Size(max = 16, message = "姓名必须为16位以内字符")
    private String name;

    @ApiModelProperty(value = "角色类型（1：超级管理员，2：普通管理员）", required = true, example = "2")
    @NotNull(message = "角色类型不能为空")
    @Range(min = 1, max = 2)
    private Integer roleType;

    @ApiModelProperty(value = "备注", example = "备注内容")
    @Size(max = 200, message = "备注必须为200位以内字符")
    private String remark;

    @ApiModelProperty(value = "状态(1：启用，2：禁用)", required = true, example = "1")
    @NotNull(message = "状态不能为空")
    @Range(min = 1, max = 2, message = "状态值错误")
    private Integer status;
}
