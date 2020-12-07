package com.github.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * OneToMany 一对多关联关系 如果关联关系时双向的，mappedBy属性必须用来标注，在拥有关联关系的实体一方中表示关系的字段名，也就是使用mappedBy属性是不维护关联关系的一方，值是拥有关联关系一方中标识关系的字段名
 * * targetEntity属性表示默认关联的实体类型，默认为当前标注的实体类
 * * cascade属性表示与此实体一对一关联的实体的联级样式类型。联级样式上当对实体进行操作时的策略。
 * * * CascadeType.PERSIST （级联新建）
 * * * CascadeType.REMOVE （级联删除）
 * * * CascadeType.REFRESH （级联刷新）
 * * * CascadeType.MERGE （级联更新）中选择一个或多个
 * * * CascadeType.ALL 表示选择全部四项
 * * fetch属性是该实体的加载方式，有两种：LAZY和EAGER。默认为惰性加载，一般也建议使用惰性加载。
 * * mappedBy属性用于双向关联实体时使用。
 *
 * JoinColumn
 * * name属性为连接两个表的表名称。若不指定，则使用默认的表名称，格式如下
 *
 *
 *
 *
 * @author jie
 * @date 2020/12/6 21:18
 */
@Data
@Entity
@Builder
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creaet;


    @Transient
    private String info;
}
