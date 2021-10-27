package com.yang.mysql.mapper.custom;

import com.yang.mysql.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomUserMapper {
    /**
     * 批量新增
     * @param userList
     * @return
     */
    public int batchInsert(@Param("userList") List<User> userList);
}
