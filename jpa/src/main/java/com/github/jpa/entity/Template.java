package com.github.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity 标识一个类为实体类
 * Table 指定表名 类名和表名一样可省略
 * Id 映射主键
 * <p>
 * GeneratedValue strategy生成主键的策略 有四种方式：
 * * GenerationType.AUTO 自动选择合适的策略（默认）
 * * GenerationType.SEQUENCE 通过序列生成主键 mysql不支持
 * * GenerationType.TABLE 通过数据表生成主键
 * * GenerationType.IDENTITY id自增 oracle不支持
 * <p>
 * TableGenerator 将当前主键的值单独保存到一个数据库的表中，主键的值每次都是从指定的表中查询来获得 新建一个主键表 sql见TablGenerator.sql
 * * name 表示该表主键生成策略的名称，它被引用在@GeneratedValue中设置的“generator”值中。
 * * table 表示表生成策略所持久化的表名，例如，这里表使用的是数据库中的“tb_generator”。
 * * catalog和schema 具体指定表所在的目录名或是数据库名。
 * * pkColumnName 表示在持久化表中，该主键生成策略所对应键值的名称。例如在“tb_generator”中将“gen_name”作为主键的键值
 * * valueColumnName 表示在持久化表中，该主键当前所生成的值，它的值将会随着每次创建累加。例如，在“tb_generator”中将“gen_value”作为主键的值
 * * pkColumnValue 表示在持久化表中，该生成策略所对应的主键。例如在“tb_generator”表中，将“gen_name”的值为“CUSTOMER_PK”。
 * * initialValue 表示主键初识值，默认为0。
 * * allocationSize 表示每次主键值增加的大小，例如设置成1，则表示每次创建新记录后自动加1，默认为50。
 * * UniqueConstraint与@Table标记中的用法类似
 * *
 * Column 标注说明
 * * name 用来指定属性和数据库字段的映射
 * * length 表示字段的长度，当字段的类型为varchar时，该属性才有效，默认为255个字符
 * * nullable 表示该字段是否可以为null值，默认为true
 * * unique 表示该字段是否为唯一标识，默认为false。如果表中有一个字段需要唯一标识，则既可以使用该标记，也可以使用@Table标记中的@UniqueConstraint
 * * insertable 表示在使用“INSERT”脚本插入数据时，是否需要插入该字段的值
 * * updatable 表示在使用“UPDATE”脚本插入数据时，是否需要更新该字段的值。insertable和updatable属性一般多用于只读的属性，例如主键和外键等。这些字段的值通常是自动生成
 * * columnDefinition 表示创建表时，该字段创建的SQL语句，一般用于通过Entity生成表定义时使用。（也就是说，如果DB中表已经建好，该属性没有必要使用
 * * table 性定义了包含当前字段的表名
 * * precision和scale 表示精度，当字段类型为double时，precision表示数值的总长度，scale表示小数点所占的位数
 * <p>
 * Basic 自动将字段映射到表默认省略
 * <p>
 * Transient 表示不将该属性映射至数据库中
 * <p>
 * Temporal 表示时间精度
 * *TemporalType.DATE 日期
 * *TemporalType.TIME 时间
 * *TemporalType.TIMESTAMP 日期时间
 *
 * @author jie
 * @date 2020/12/6 21:18
 */
@Data
@Entity
@Builder
@Table(name = "template")
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    @TableGenerator(name = "id_generator",
            table = "tb_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "CUSTOMER_PK",
            valueColumnName = "gen_value",
            allocationSize = 100)
    private Integer id;

    @Basic
    private String name;

    @Column(name = "age", length = 3, nullable = false)
    private Integer age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creaet;

    @Transient
    private String info;
}
