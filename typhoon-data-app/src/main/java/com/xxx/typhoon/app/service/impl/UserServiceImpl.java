package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.util.JwtUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.User;
import com.xxx.typhoon.app.mapper.UserMapper;
import com.xxx.typhoon.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
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
    public User login(String userName, String password) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name", userName);
        User user = userMapper.selectOne(wrapper);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                //登录成功生成token
                user.setToken(jwtUtil.createToken(user));
                return user;
            }
        }
        return user;
    }

    @Override
    public Integer updatePassword(Long userId, String password) {
        return null;
    }

    @Override
    public Integer adminDeleteUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getUserList(Long userId) {
        return null;
    }

    @Override
    public User getUserInfo(Long userId) {
        return null;
    }

    @Override
    public Integer adminUpdateUserAuth(Long userId) {
        return null;
    }
}
