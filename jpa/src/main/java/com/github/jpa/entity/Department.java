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
@Table(name = "tb_department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "dep_name")
    private String depName;
    @OneToOne
    @JoinColumn(name = "mgr_id",unique = true)
    private Manner manner;
}
