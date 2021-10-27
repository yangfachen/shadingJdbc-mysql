package com.yang.mysql.dao;

import com.yang.mysql.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public void add(User user);

    public void update(User user);

    public int delete(long id);

    public List<User> select(Map map);
}
