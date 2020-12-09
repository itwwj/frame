package com.github.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author jie
 */
@Data
@Entity
@Builder
@Table(name = "tb_manner")
@NoArgsConstructor
@AllArgsConstructor
public class Manner {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "mgr_name")
    private String mgrName;
    /**
     * 对于不维护的关联关系，没有外键的一方使用 @OneToOne来映射，需要设置mappedBy=关联的外键，能降低sql语句
     */
    @OneToOne(mappedBy = "manner")
    private Department department;
}
