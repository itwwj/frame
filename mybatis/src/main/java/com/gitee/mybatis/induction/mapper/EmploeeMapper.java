package com.gitee.mybatis.induction.mapper;

import com.gitee.mybatis.induction.entity.Employee;
import com.gitee.mybatis.induction.entity.EmployeePlus;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * mybatis 增删改 默认允许定义以下几种返回值
 * Integer、Long、Boolean
 *
 * @author jie
 */
public interface EmploeeMapper {

    Employee selectOne(Long id);

    Integer insert(Employee employee);

    Integer updata(Employee employee);

    Integer delete(Long id);

    Long insertReturnKey(Employee employee);

    @MapKey("id")
    Map<Long, Employee> selectToMap(String name);

    EmployeePlus selectEmpPlus(Long id);

    List<Employee> selectByDynamic(Employee employee);

    int updataByDynamic(@Param("id") Long id, @Param("emp") Employee employee);

    List<Employee> selectInIds(@Param("ids") List<Long> ids);

    @Select("select * from tb_test where id=#{id}")
    Employee findOne(Long id);

    /**
     * @param employee
     * @return
     */
    @Select("select * from tb_test where name=#{name} and age=#{age}")
    List<Employee> selectByEntity(Employee employee);

    /**
     * 多个参数mybatis会做特殊处理 不能直接使用#{name} #{age}
     *
     * @param name
     * @param age
     * @return
     */
    @Select("select * from tb_test where name=#{name} and age=#{age}")
    List<Employee> selectByNameAndAge(@Param("name") String name, @Param("age") Integer age);

    /**
     * 用map封装参数
     *
     * @param employee
     * @return
     */
    @Select("select * from tb_test where name=#{name} and age=#{age}")
    List<Employee> selectByMap(Map<String, Object> employee);

    /**
     * 复杂参数封装
     *
     * @param employee
     * @return
     */
    @Select("select * from tb_test where name=#{name} and age=#{emp.age}")
    List<Employee> selectByExmaple(@Param("name") String name, @Param("emp") Employee employee);

    /**
     * 集合参数
     * Collection的key是collection
     * List的key是list
     * 数组的key是array
     *
     * @param l
     * @return
     */
    @Select("select * from tb_test where name=#{list[0]} and age=#{list[1]}")
    List<Employee> selectByList(List<Object> l);
}
