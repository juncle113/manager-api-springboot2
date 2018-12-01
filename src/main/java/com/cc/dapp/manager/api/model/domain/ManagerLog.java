package com.cc.dapp.manager.api.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
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
