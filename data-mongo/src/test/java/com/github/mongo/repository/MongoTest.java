package com.github.mongo.repository;

import com.alibaba.fastjson.JSONArray;
import com.github.mongo.config.RemoveDollarOperation;
import com.github.mongo.entity.Company;
import com.github.mongo.entity.Department;
import com.github.mongo.entity.Employee;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void initData() {
        // 公司
        Company company = new Company();
        company.setCompanyName("XXX公司");
        company.setMobile("023-66668888");
        mongoTemplate.save(company);

        // 部门
        Department department = new Department();
        department.setDepartmentName("XXX信息开发系统");
        department.setCompany(company);
        department.setEmployeeList(Collections.emptyList());
        mongoTemplate.save(department);

        // 员工
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeName("张一");
        employee1.setPhone("159228359xx");
        employee1.setDepartment(department);
        employeeList.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeName("张二");
        employee2.setPhone("159228358xx");
        employee2.setDepartment(department);
        employeeList.add(employee2);
        mongoTemplate.insert(employeeList, Employee.class);

        department.setEmployeeList(employeeList);
        mongoTemplate.save(department);
    }
    /**
     * 员工表关联部门表
     */
    @Test
    public void twoTableQuery() {
        // 1、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation = new RemoveDollarOperation("newDepartmentFieldName", "department");

        // 2、使用mongodb $lookup实现左连接部门表
        LookupOperation lookupOperation = LookupOperation.newLookup().from("department")
                .localField("newDepartmentFieldName.id").foreignField("_id").as("newDepartment");

        // $match条件筛选
        // MatchOperation matchOperation = new MatchOperation(Criteria.where("newDepartment.departmentName").is("信息开发系统"));

        // 3、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(Employee.class, removeDollarOperation, lookupOperation);
        // TypedAggregation aggregation = Aggregation.newAggregation(Employee.class, removeDollarOperation, lookupOperation, matchOperation);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

        System.out.println(JSONArray.toJSONString(results.getMappedResults()));
    }

    /**
     * 员工表关联部门表，部门表关联公司表
     */
    @Test
    public void threeTableQuery() {
        // 1、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newDepartmentFieldName", "department");

        // 2、使用mongodb $lookup实现左连接部门表
        LookupOperation lookupOperation1 = LookupOperation.newLookup().from("department")
                .localField("newDepartmentFieldName.id").foreignField("_id").as("newDepartment");

        // 3、使用$unwind展平步骤二中的左连接的department表的"newDepartment"
        UnwindOperation unwindOperation = new UnwindOperation(Fields.field("$newDepartment"));

        // 4、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation2 = new RemoveDollarOperation("newCompanyFieldName", "newDepartment.company");

        // 5、使用mongodb $lookup实现左连接公司表
        LookupOperation lookupOperation2 = LookupOperation.newLookup().from("company")
                .localField("newCompanyFieldName.id").foreignField("_id").as("newCompany");

        MatchOperation matchOperation = new MatchOperation(Criteria.where("newCompany.companyName").is("XXX公司"));

        // 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(Employee.class,
                removeDollarOperation1, lookupOperation1,
                unwindOperation,
                removeDollarOperation2, lookupOperation2,
                matchOperation);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

        System.out.println(JSONArray.toJSONString(results.getMappedResults()));
    }
    /**
     * 查询部门中的所有员工，部门关联多个员工
     */
    @Test
    public void oneToManyTableQuery() {
        // 1、展平“多”的一方
        UnwindOperation unwindOperation = new UnwindOperation(Fields.field("employeeList"));

        // 2、消除@DBRef引用对象中的"$id"的"$"符号
        RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newEmployeeFieldName", "employeeList");

        // 3、使用mongodb $lookup实现左连接员工表
        LookupOperation lookupOperation1 = LookupOperation.newLookup().from("employee")
                .localField("newEmployeeFieldName.id").foreignField("_id").as("newEmployee");

        // 筛选条件（非必须，看自己是否需要筛选）
        MatchOperation matchOperation = new MatchOperation(Criteria.where("newEmployee.employeeName").is("张一"));

        // 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(Employee.class,
                unwindOperation,
                removeDollarOperation1, lookupOperation1,
                matchOperation);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

        System.out.println(JSONArray.toJSONString(results.getMappedResults()));
    }
}
