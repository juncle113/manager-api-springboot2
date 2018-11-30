package com.cc.dapp.manager.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AdminLoginVO", description = "登录VO")
@Data
public class AdminLoginVO {

    @ApiModelProperty(value = "id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "token", required = true, example = "abcdefgh12345678")
    private String token;

}
