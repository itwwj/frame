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
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField("manager_id")
    private String mannagerId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updataTime;
    /**
     * 乐观锁版本
     */
    @Version
    private Integer version;
    /**
     * TableLogic表示这是个逻辑删除字段
     * TableField(select = false) 表示不查询deleted字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;
}
