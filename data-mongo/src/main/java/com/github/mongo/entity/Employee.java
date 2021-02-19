package com.github.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jie
 */
@Data
@Builder
@Document("employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String id;

    private String employeeName;

    private String phone;

    @DBRef
    private Department department;
}
