package com.github.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jie
 */
@Data
@Builder
@Document("company")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    private String id;

    private String companyName;

    private String mobile;
}
