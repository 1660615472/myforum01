package com.cheer.bbs.service;


import com.cheer.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<User> getList();

        User getUser(String name, String password);

    User getUserByUserName(@Param("name") String name);

    int insUser(User pro);

    int update(User pro);

    int delete(int id);
}
