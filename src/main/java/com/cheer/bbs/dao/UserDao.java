package com.cheer.bbs.dao;


import com.cheer.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> getList();

    User getUser(@Param("name") String name, @Param("password") String password);

    User getUserByUserName(@Param("name") String name);

    int insUser(User pro);

    int update(User pro);

    int delete(int id);
}
