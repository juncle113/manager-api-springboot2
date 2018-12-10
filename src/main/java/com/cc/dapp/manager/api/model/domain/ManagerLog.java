package com.cc.dapp.manager.api.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 管理日志表
 *
 * @author sunli
 * @date 2018/12/07
 */
@Data
@Entity
@org.hibernate.annotations.Table(appliesTo = "manager_log", comment = "管理日志表")
public class ManagerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int unsigned COMMENT 'id'")
    private Integer id;

    @Column(columnDefinition = "varchar(200) COMMENT '备注'")
    private String remark;

    @Column(columnDefinition = "datetime(3) COMMENT '创建时间'")
    private Timestamp createdTime;

    @Column(columnDefinition = "int unsigned COMMENT '创建人id'")
    private Integer createdById;
}
