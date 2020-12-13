package com.it.mybatis;

import com.it.Mapper.UserMapper;
import com.it.Stockmarket02Application;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Stockmarket02Application.class)
public class MybatisTest {
    @Autowired
    private   UserMapper userMapper;
    @Test
   public void  testdemo01(){

        System.out.println("hahdfadshfhads");
//        System.out.println( "没打印？？=="+userMapper.queryUserList());


    }

}
