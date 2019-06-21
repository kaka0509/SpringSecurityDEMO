package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 覆盖默认的UserDetailsService
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆用户名:" + username);
        // 查找用户信息
        // 构造函数可以指定用户是否可用、被冻结、密码过期等
        // 也可以返回自定义的UserDetails
        String password = passwordEncoder.encode("123456");
        System.out.println("数据库密码是: " + password);
        /**
         * 第二个参数传入数据库中的用户名密码，如果前端传过来的不匹配则会抛异常：坏的凭证
         */
        return new User(username, password, //认证信息
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));//权限信息
    }
}
