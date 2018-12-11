package com.cpt.dapp.manager.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息
 *
 * @author sunli
 * @date 2018/12/07
 */
@ApiModel(value = "UserVO", description = "用户信息")
@Data
public class UserVO extends BaseVO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "姓名", required = true, example = "管理员")
    private String name;

}
