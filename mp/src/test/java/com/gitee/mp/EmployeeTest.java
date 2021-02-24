package com.gitee.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.mp.entity.Employee;
import com.gitee.mp.mapper.EmployeeMapper;
import com.gitee.mp.mapper.EmployeePlusMapper;
import com.gitee.mp.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeTest {

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private EmployeeService service;

    @Test
    public void inert() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int age = random.nextInt(30);
            mapper.insert(Employee.builder().name(UUID.randomUUID().toString().substring(0, 5)).age(age).email("xxxxxxx").did(2).build());

        }
    }

    @Test
    public void select() {
        Employee employee = mapper.selectById(350);
        System.out.println(employee);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<Employee> query = Wrappers.query();
        query.like("name", "ax");
        List<Employee> list = service.list(query);
        list.stream().forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<Employee> query = Wrappers.query();
        query.like("name", "1").or().orderByAsc("age");
        List<Employee> list = service.list(query);
        list.stream().forEach(System.out::println);
    }

    /**
     * between
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<Employee> query = Wrappers.query();
        query.between("age", 20, 30).orderByAsc("age");
        List<Employee> list = service.list(query);
        list.stream().forEach(System.out::println);
    }

    /**
     * 自定义返回对象
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<Employee> query = Wrappers.query();
        query.select("count(1) as count").groupBy("d_id");
        List<Map<String, Object>> maps = service.listMaps(query);
        maps.stream().forEach((s) -> {
            for (String s1 : s.keySet()) {
                System.out.println(s1 + "      " + s.get(s1));
            }
        });
    }

    /**
     * LambdaQueryWrapper
     */
    @Test
    public void selectBylambda() {
        LambdaQueryWrapper<Employee> query = Wrappers.lambdaQuery();
        query.like(Employee::getName, "a").between(Employee::getAge, 20, 30);
        List<Employee> list = service.list(query);
        list.stream().forEach(System.out::println);
    }

    /**
     * 自定义sql语句
     */
    @Test
    public void selectBycustomSql() {
        LambdaQueryWrapper<Employee> query = Wrappers.lambdaQuery();
        query.like(Employee::getName, "a").between(Employee::getAge, 20, 30);
        List<Employee> list = mapper.selectAll(query);
        list.stream().forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectByPage() {
        LambdaQueryWrapper<Employee> query = Wrappers.lambdaQuery();
        query.lt(Employee::getAge, 30);
        IPage<Employee> page = new Page<>(1, 10);
        IPage<Employee> employeeIPage = service.page(page, query);
        List<Employee> list = employeeIPage.getRecords();
        list.stream().forEach(System.out::println);
    }

    @Autowired
    private EmployeePlusMapper plusMapper;

    @Test
    public void deleteAll() {
        Integer integer = plusMapper.deleteAll();
        System.out.println(integer);

    }
}
