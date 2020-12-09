package com.github.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 *
 * CompoundIndex 复合索引
 * * 升序1 降序-1
 * @author jie
 * @date 2020/12/6 21:18
 */
@Data
@Builder
@Document("tb_user")
@CompoundIndex(def ="{'createId':1,'name':-1}" )
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Field("name")
    private String name;

    private String email;

    private Date creaet;
    /**
     * 添加索引
     */
    @Indexed
    private Integer createId;

    private String info;
}
