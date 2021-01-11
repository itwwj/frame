package com.github.mongo.controller;

import com.github.mongo.entity.User;
import com.github.mongo.service.Userservice;
import lombok.Data;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

/**
 * @author jie
 */
@Data
@RestController
public class UserController {
    private final Userservice userservice;

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userservice.save(user);
    }

    @GetMapping("/del/{id}")
    public void del(@PathVariable String id) {
        userservice.delByQuery(Query.query(Criteria.where("id").is(id)));
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userservice.update(user);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable String id) {
        return userservice.findById(id);
    }

}
