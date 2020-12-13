package com.it.Controller;

import com.it.Mapper.UserMapper;
import com.it.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ConfigurationProperties(prefix = "person")
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    private String name;
    private String age;
   /* @RequestMapping("login")
    public String login(){
        return name+age;

    }*/

    @RequestMapping("login2")
    public List<User> login2(){

        return userMapper.queryUserList();

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
