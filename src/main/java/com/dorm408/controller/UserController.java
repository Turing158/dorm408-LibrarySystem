package com.dorm408.controller;

import com.dorm408.entity.RequestParam;
import com.dorm408.entity.Result;
import com.dorm408.entity.User;
import com.dorm408.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(String user,String password){
        System.out.println(user+" "+password);
        return userService.userLogin(user,password);
    }

    @PostMapping("/reg")
    public Result register(@RequestBody User user){
        return userService.UserRegister(user.getUser_id(),user.getUser_password());
    }

    @PostMapping("/countUser")
    public Result countUser(){
        return userService.countUser();
    }

    @PostMapping("/getUser")
    public Result findAllUser(int page){
        return userService.findAllUser(page);
    }


    @PostMapping("/updatePermission")
    public Result updateUserPermission(@RequestBody RequestParam requestParam){
        return userService.updateUserPermission(requestParam.getUser_id(),requestParam.getPermission());
    }


    @PostMapping("/delUser")
    public Result deleteUser(@RequestBody RequestParam requestParam){
        return userService.deleteUser(requestParam.getUser_id());
    }
}
