package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.User;
import com.xxx.typhoon.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
