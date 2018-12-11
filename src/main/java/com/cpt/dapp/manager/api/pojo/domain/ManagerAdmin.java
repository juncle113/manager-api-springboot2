package com.cpt.dapp.manager.api.pojo.domain;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 管理员信息表
 * 删除处理为逻辑删除
 * 查询处理只取得未被删除的记录
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
@Entity
@org.hibernate.annotations.Table(appliesTo = "manager_admin", comment = "管理员信息表")
@SQLDelete(sql = "update manager_admin set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class ManagerAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int unsigned COMMENT 'id'")
    private Integer id;

    @Column(columnDefinition = "varchar(16) COMMENT '用户名'")
    private String userName;

    @Column(columnDefinition = "varchar(32) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "varchar(16) COMMENT '姓名'")
    private String name;

    @Column(columnDefinition = "int unsigned COMMENT '角色类型（1：系统管理员，2：超级管理员，3：普通管理员）'")
    private Integer roleType;

    @Column(columnDefinition = "varchar(200) COMMENT '备注'")
    private String remark;

    @Column(columnDefinition = "int unsigned COMMENT '状态（1：启用，2：禁用）'")
    private Integer status;

    @Column(columnDefinition = "bit COMMENT '删除标记（0：未删除，1：已删除）'")
    private Boolean deleted;

    @Column(columnDefinition = "datetime(3) COMMENT '创建时间'")
    private Timestamp createdTime;

    @Column(columnDefinition = "int unsigned COMMENT '创建人id'")
    private Integer createdById;

    @Column(columnDefinition = "datetime(3) COMMENT '修改时间'")
    private Timestamp modifiedTime;

    @Column(columnDefinition = "int unsigned COMMENT '修改人id'")
    private Integer modifiedById;
}
