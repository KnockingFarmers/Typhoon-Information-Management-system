package com.xxx.typhoon.app.controller;

import com.xxx.typhoon.app.entity.User;
import com.xxx.typhoon.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User login(String userName,String password){
        return userService.login(userName,password);
    }

    @PostMapping("/getUserInfo")
    public User getUserInfo(String userId){
        return userService.getUserInfo(Long.valueOf(userId));
    }

    @PostMapping("/getUserList")
    public List<User> getUserList(String userId){
        return userService.getUserList(Long.valueOf(userId));
    }


    @PostMapping("/admin/deleteUserById")
    public Integer deleteUserById(String deleteUserId, String actionUserId){
        return userService.adminDeleteUser(Long.valueOf(deleteUserId),Long.valueOf(actionUserId));
    }

    @PostMapping("/admin/updateUserAuth")
    public Integer adminUpdateUserAuth(String updateUserId, String actionUserId){
        return userService.adminUpdateUserAuth(Long.valueOf(updateUserId),Long.valueOf(actionUserId));
    }

    @PostMapping("/updatePassword")
    public Integer updatePassword(String userId,String password,String token){
        return userService.updatePassword(Long.valueOf(userId),password,token);
    }




}
