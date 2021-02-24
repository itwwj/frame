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
@TableName("tb_dept")
public class Dept {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("d_name")
    private String dname;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "updata_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updataTime;
}
