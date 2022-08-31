package com.xxx.typhoon.app.service;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
    CommonResult<User> login(String userName,String password);
}
