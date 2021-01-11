package com.github.redis;

import com.github.redis.properies.CacheHashKey;
import com.github.redis.repository.SuperBaseRedisOps;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author jie
 */
@Data
@SpringBootTest
@RunWith(SpringRunner.class)
public class Rtest {

    @Autowired
    private SuperBaseRedisOps superBaseRedisOps;

    @Test
    public void keys() {
        Set<String> keys = superBaseRedisOps.keys("*");
        System.out.println(keys);
    }
    @Test
    public void add(){
        superBaseRedisOps.hSet(new CacheHashKey("axiba","aaaa"),"000");
    }
    @Test
    public void get(){
        Object o = superBaseRedisOps.hGet(new CacheHashKey("axiba", "aaaa"));
        System.out.println(o.toString());
    }
    @Test
    public void del(){
        superBaseRedisOps.hDel(new CacheHashKey("axiba","aaaa"));
    }
}
