package com.gitee.mybatis.induction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author jie
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePlus extends Employee {
    private Dept dept;
}
