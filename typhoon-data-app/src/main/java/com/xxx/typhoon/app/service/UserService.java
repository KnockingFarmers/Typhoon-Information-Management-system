package com.xxx.typhoon.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.typhoon.app.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-08-30
 */
public interface UserService extends IService<User> {

    /**
     * 登录业务方法
     * @param userName
     * @param password
     * @return
     */
    User login(String userName,String password);


    /**
     * 修改密码
     * @param userId
     * @param password
     * @param token
     * @return
     */
    Integer updatePassword(Long userId,String password,String token);

    /**
     * 管理员删除用户
     * @param deleteUserId
     * @param actionUserId
     * @return
     */
    Integer  adminDeleteUser(Long deleteUserId,Long actionUserId);

    /**
     * 获取用户列表
     * @param userId
     * @return
     */
    List<User> getUserList(Long userId);

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    User getUserInfo(Long userId);

    /**
     * 修改用户权限
     * @param updateUserId
     * @param actionUserId
     * @return
     */
    Integer adminUpdateUserAuth(Long updateUserId,Long actionUserId);
}
