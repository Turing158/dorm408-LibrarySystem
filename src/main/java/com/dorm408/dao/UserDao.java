package com.dorm408.dao;

import com.dorm408.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll(int page);
    int countUser();
    int existUser(String id);
    User findUser(String id);
    int insertUser(User user);
    int deleteUser(String id);
    int updateUserPermission(String user,int permission);
    int existLogByUser(String user);
}
