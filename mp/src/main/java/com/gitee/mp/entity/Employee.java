package com.gitee.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jie
 */
@Data
@Builder
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
    @TableField("d_id")
    private Integer did;

    /**
     * 备注，不与数据库做映射
     */
    @TableField(exist = false)
    private String remark;

    @TableField(value = "create_time",fill = FieldFill. INSERT)
    private LocalDateTime createTime;
    @TableField(value = "updata_time",fill = FieldFill. INSERT_UPDATE)
    private LocalDateTime updataTime;
}
