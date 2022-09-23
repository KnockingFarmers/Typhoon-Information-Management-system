package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.util.JwtUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.User;
import com.xxx.typhoon.app.mapper.UserMapper;
import com.xxx.typhoon.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    public Integer updatePassword(Long userId, String password,String token) {
        User user = userMapper.selectById(userId);
        Map map = jwtUtil.analyzeToken(token);
        String id = (String) map.get("userId");
        int result=0;
        if (!user.equals(null)&&StringUtils.isEmpty(password)) {
            Integer auth = (Integer) map.get("authority");
            if (String.valueOf(userId).equals(id)||auth>=1){
                    user.setPassword(password);
                result= userMapper.updateById(user);
                }
        }

        return result;
    }

    @Override
    public Integer adminDeleteUser(Long deleteUserId, Long actionUserId) {

        int result =0;
        if (!actionUserId.equals(null)) {
            User user = userMapper.selectById(actionUserId);
            if (!user.equals(null) && user.getAuthority() >= 1) {

                Lock lock = new ReentrantLock();
                lock.lock();
                try {
                    result= userMapper.deleteById(deleteUserId);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }

        return result;
    }

    @Override
    public List<User> getUserList(Long userId) {
        return null;
    }

    @Override
    public User getUserInfo(Long userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Integer adminUpdateUserAuth(Long updateUserId, Long actionUserId) {

        if (!actionUserId.equals(null)) {
            User user = userMapper.selectById(actionUserId);
            if (!user.equals(null) && user.getAuthority() >= 1) {
                Lock lock = new ReentrantLock();
                lock.lock();
                try {

                    user.setAuthority(1);
                    return userMapper.updateById(user);

                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
        return 0;
    }
}
