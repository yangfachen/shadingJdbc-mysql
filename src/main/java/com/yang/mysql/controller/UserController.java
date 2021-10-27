package com.yang.mysql.controller;

import com.google.common.collect.Lists;
import com.yang.mysql.common.RestItemResult;
import com.yang.mysql.dao.UserDao;
import com.yang.mysql.domain.User;
import com.yang.mysql.mapper.custom.CustomUserMapper;
import com.yang.mysql.mapper.generated.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomUserMapper customUserMapper;

    @GetMapping("/query")
    public RestItemResult query(){
        List<User> list = userDao.select(new HashMap());

        return new RestItemResult(list);
    }

    @GetMapping("/get/{id}")
    public RestItemResult get(@PathVariable("id") long id){

        User user = userMapper.selectByPrimaryKey(id);

        return new RestItemResult(user);
    }

    @PostMapping("/batchInsert")
    public void batchInsert(){
        int num = 100000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            User u = new User();
            u.setName("yang"+i);
            userList.add(u);
        }
        List<List<User>> partition = Lists.partition(userList, 1000);
        partition.forEach(users -> customUserMapper.batchInsert(users));
    }


}
