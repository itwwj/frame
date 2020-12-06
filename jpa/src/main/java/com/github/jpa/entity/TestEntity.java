package com.github.jpa.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Entity 标识一个类为实体类
 * Table 指定表名 类名和表名一样可省略
 * Id 映射主键
 * GeneratedValue strategy生成主键的策略 有四种方式：
 *           GenerationType.AUTO 自动选择合适的策略（默认）
 *           GenerationType.SEQUENCE 通过序列生成主键 mysql不支持
 *           GenerationType.TABLE 通过数据表生成主键
 *           GenerationType.IDENTITY id自增 oracle不支持
 * Basic 自动将字段映射到表默认省略
 * @author jie
 * @date 2020/12/6 21:18
 */
@Data
@Entity
@Builder
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Basic
    private String name;
    @Column
    private String email;

}
