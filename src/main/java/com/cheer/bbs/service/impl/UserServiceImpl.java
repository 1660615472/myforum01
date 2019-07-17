package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.UserDao;
import com.cheer.bbs.pojo.User;
import com.cheer.bbs.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> getList() {//测试OK
        List<User> list = userDao.getList();
        return list;
    }

    @Override
    public User getUser(String name, String password) {
        User pro = this.userDao.getUser(name, password);
        return pro;
    }

    @Override
    public User getUserByUserName(String name) {
        User pro = this.userDao.getUserByUserName(name);
        return pro;
    }

    @Override
    public int insUser(User pro) {//测试OK
        int i = this.userDao.insUser(pro);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }

    }

    @Override
    public int update(User pro) {
       int i = this.userDao.update(pro);
        if(i>0){
            System.out.println("更新成功！");
            return i;
        }else {
            return i;
        }
    }

    @Override
    public int delete(int id) {
        int i = this.userDao.delete(id);
        if(i>0){
            System.out.println("更新成功！");
            return i;
        }else {
            return i;
        }
    }
}
