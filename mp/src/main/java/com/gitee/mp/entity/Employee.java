package com.gitee.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_emp")
public class Employee {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;
    private Integer age;
    private String email;
}
