package com.github.jpa.repository;

import com.github.jpa.entity.Department;
import com.github.jpa.entity.Manner;
import com.github.jpa.repository.DepartmentDao;
import com.github.jpa.repository.MannerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private MannerDao mannerDao;
    @Test
    public void save() {
        Manner manner = new Manner();
        Department department=new Department();
        manner.setMgrName("xxx");
        department.setDepName("ooo");
        manner.setDepartment(department);
        department.setManner(manner);
        Department save = departmentDao.save(department);
        //mannerDao.save(manner);
    }
}