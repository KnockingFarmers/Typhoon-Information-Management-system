package com.xxx.typhoon.auth;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author 甘龙
 * @Date 2022/8/29 15:41
 * @PackageName:com.xxx.typhoon.auth
 * @ClassName: SecurityConfig
 * @Description: 权限管理配置类
 * @Version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/管理员功能").hasRole("admin")
                .antMatchers("/管理员功能").hasRole("admin")
                .antMatchers("/管理员功能").hasRole("admin");

        //没有登录的用户跳转到登录页面
        http.formLogin();
    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin")).roles("admin")
                .and()
                .withUser("123")
                .password(new BCryptPasswordEncoder().encode("123")).roles();
    }
}
