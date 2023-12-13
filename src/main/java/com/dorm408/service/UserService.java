package com.dorm408.service;

import com.dorm408.dao.UserDaoImpl;
import com.dorm408.entity.Result;
import com.dorm408.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDaoImpl userDao;

    public Result userLogin(String userId,String password){
        Result result = new Result();
        if(userDao.existUser(userId) == 1){
            User user = userDao.findUser(userId);
            if(user.getUser_password().equals(password)){
                result.setStatus("success");
                result.setObject(user);
                return result;
            }
            result.setStatus("密码错误！");
            return result;
        }
        result.setStatus("该用户不存在！");
        return result;
    }


    public Result UserRegister(String userId,String password){
        Result result = new Result();
        if(userDao.existUser(userId) == 0){
            int status = userDao.insertUser(new User(userId,"新用户",password,0));
            if(status == 1){
                result.setStatus("success");
                return result;
            }
            result.setStatus("error");
            return result;
        }
        result.setStatus("该用户已经存在！");
        return result;
    }

    public Result countUser(){
        Result result = new Result();
        result.setStatus("success");
        result.setObject(userDao.countUser());
        return result;
    }

    public Result findAllUser(int page){
        Result result = new Result();
        result.setStatus("success");
        result.setObject(userDao.findAll((page-1)*10));
        return result;
    }

    public Result updateUserPermission(String user,int permission){
        Result result = new Result();
        userDao.updateUserPermission(user,permission);
        result.setStatus("success");
        return result;
    }

    public Result deleteUser(String user){
        Result result = new Result();
        if(userDao.existLogByUser(user) == 0){
            userDao.deleteUser(user);
            result.setStatus("success");
            return result;
        }
        result.setStatus("该用户存在书未还！");
        return result;
    }

}
