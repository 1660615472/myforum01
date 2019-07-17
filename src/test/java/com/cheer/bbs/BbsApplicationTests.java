package com.cheer.bbs;

import com.cheer.bbs.dao.MesDao;
import com.cheer.bbs.dao.UserDao;
import com.cheer.bbs.dao.TitleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsApplicationTests {

    @Resource
    UserDao userDao;

    @Resource
    MesDao mesDao;

    @Resource
    TitleDao titleDao;
    @Test
    public void contextLoads() {
    }

}
