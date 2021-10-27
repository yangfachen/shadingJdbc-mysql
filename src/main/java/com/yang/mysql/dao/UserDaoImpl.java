package com.yang.mysql.dao;

import com.yang.mysql.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(User user) {
        String sql = "insert into user(name)values(?)";
        jdbcTemplate.update(sql,user.getName());

    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ? where id = ? ";
        jdbcTemplate.update(sql,user.getName(),user.getId());
    }

    @Override
    public int delete(long id) {
        String sql = "delete from user where id = ? ";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<User> select(Map map) {
        String sql = " select * from user ";
        RowMapper rowMapper = new BeanPropertyRowMapper(User.class);
        List<User> list = jdbcTemplate.query(sql,rowMapper,new String[]{});
        return list;
    }
}
