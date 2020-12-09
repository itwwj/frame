package com.github.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 多对一表关系
 * 多个订单对应一个用户
 * ManyToOne 表示多对一映射
 * JoinColumn 的name属性映射外键列的列名
 *
 *
 * OneToMany 一对多关联关系 如果关联关系时双向的，mappedBy属性必须用来标注，在拥有关联关系的实体一方中表示关系的字段名，也就是使用mappedBy属性是不维护关联关系的一方，值是拥有关联关系一方中标识关系的字段名
 * * targetEntity属性表示默认关联的实体类型，默认为当前标注的实体类
 * * cascade属性表示与此实体一对一关联的实体的联级样式类型。联级样式上当对实体进行操作时的策略。
 * * * CascadeType.PERSIST （级联新建）
 * * * CascadeType.REMOVE （级联删除）
 * * * CascadeType.REFRESH （级联刷新）
 * * * CascadeType.MERGE （级联更新）中选择一个或多个
 * * * CascadeType.ALL 表示选择全部四项
 * * fetch属性是该实体的加载方式，有两种：LAZY和EAGER。默认为惰性加载，一般也建议使用惰性加载。
 * * mappedBy 属性用于双向关联实体时使用，指定由谁维护关联关系 。注意在使用该属性时就不能使用JoinColumn注解
 *
 *
 *
 * @author jie
 */
@Data
@Entity
@Builder
@Table(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @Column(name = "order_name")
    private String orderName;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
