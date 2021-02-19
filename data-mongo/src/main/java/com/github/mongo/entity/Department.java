package com.github.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author jie
 */
@Data
@Builder
@Document("department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private String id;

    private String departmentName;

    @DBRef
    private Company company;

    @DBRef
    private List<Employee> employeeList;
}
