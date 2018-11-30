package com.cc.dapp.manager.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@ApiModel(value = "AdminVO", description = "管理员VO")
@Data
public class AdminVO {

    @ApiModelProperty(value = "id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "用户名", required = true, example = "root")
    private String userName;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "角色类型", required = true, example = "1")
    private Integer roleType;

    @ApiModelProperty(value = "角色类型名", required = true, example = "超级管理员")
    private String roleTypeName;

    @ApiModelProperty(value = "备注", example = "备注内容")
    private String remark;

    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;

    @ApiModelProperty(value = "状态名", required = true, example = "有效")
    private String statusName;

    @ApiModelProperty(value = "创建时间", required = true, example = "2018-11-30 11:22:33")
    private Timestamp createdTime;

    @ApiModelProperty(value = "创建人id", required = true, example = "1")
    private Integer createdBy;

    @ApiModelProperty(value = "创建人", required = true, example = "root")
    private String createdByUserName;

    @ApiModelProperty(value = "修改时间", required = true, example = "2018-11-30 11:22:33")
    private Timestamp modifiedTime;

    @ApiModelProperty(value = "修改人id", required = true, example = "1")
    private Integer modifiedBy;

    @ApiModelProperty(value = "修改人", required = true, example = "root")
    private String modifiedByUserName;

}
