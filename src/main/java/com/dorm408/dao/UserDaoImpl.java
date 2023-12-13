package com.dorm408.dao;

import com.dorm408.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll(int page) {
        String sql = "select * from user limit ?,10";
        Object[] args = {page};
        return jdbcTemplate.query(sql,args,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int countUser() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }


    @Override
    public int existUser(String id) {
        String sql = "select count(*) from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

    @Override
    public User findUser(String id) {
        String sql = "select * from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into user values(?,?,?,?)";
        Object[] args = {user.getUser_id(),user.getUser_name(),user.getUser_password(),user.getUser_permission()};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int deleteUser(String id) {
        String sql = "delete from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int updateUserPermission(String user, int permission) {
        String sql = "update user set user_permission=? where user_id=?";
        Object[] args = {permission,user};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int existLogByUser(String user) {
        String sql = "select count(*) from book_log where user_id=?";
        Object[] args = {user};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }


}
