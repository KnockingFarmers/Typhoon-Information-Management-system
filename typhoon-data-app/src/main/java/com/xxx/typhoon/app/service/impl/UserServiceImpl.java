package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.JwtUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.User;
import com.xxx.typhoon.app.mapper.UserMapper;
import com.xxx.typhoon.app.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-08-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public CommonResult<User> login(String userName, String password) {
        CommonResult<User> result=new CommonResult<>();

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_name",userName);
        User user = userMapper.selectOne(wrapper);
        if (user!=null) {
            if (user.getPassword().equals(password)){
                //登录成功生成token
                user.setToken(jwtUtil.createToken(user));

                result.setMessage("登录成功");
                result.setCode(200);
                result.setData(user);
            }else {
                result.setMessage("密码错误");
                result.setCode(500);
            }
            return result;
        }
        result.setCode(404);
        result.setMessage("没有该用户");

        return result;
    }
}
