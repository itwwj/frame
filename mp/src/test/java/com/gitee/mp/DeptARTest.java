package com.gitee.mp;

import com.gitee.mp.entity.DeptAR;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeptARTest {

    @Test
    public void insert(){
        DeptAR deptAR=new DeptAR();
        deptAR.setId(123458L);
        deptAR.setDname("sasdasfasf");
        boolean insert = deptAR.insert();
        System.out.println(insert);
    }
}
