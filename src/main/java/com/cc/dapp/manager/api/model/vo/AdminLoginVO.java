package com.cc.dapp.manager.api.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AdminLoginVO", description = "登录信息")
@Data
public class AdminLoginVO extends BaseVO {

    @ApiModelProperty(value = "id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "token", required = true, example = "abcdefgh12345678")
    private String token;

//    @ApiModelProperty(value = "accessToken", required = true, example = "abcdefgh12345678")
//    private String accessToken;
//
//    @ApiModelProperty(value = "tokenType", required = true, example = "bearer")
//    private String tokenType;
//
//    @ApiModelProperty(value = "expiresIn", required = true, example = "3600")
//    private String expiresIn;
//
//    @ApiModelProperty(value = "refreshToken", required = true, example = "ijklmnop12345678")
//    private String refreshToken;
}
