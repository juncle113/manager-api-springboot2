package com.cc.dapp.manager.api.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UserVO", description = "用户VO")
@Data
public class UserVO extends BaseVO {

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "姓名", required = true, example = "管理员")
    private String name;

}
