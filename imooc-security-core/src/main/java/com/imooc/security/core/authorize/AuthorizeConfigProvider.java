package com.imooc.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 模块权限定义接口，用于剥离各个模块的Security配置
 */
public interface AuthorizeConfigProvider {

    // 在修改HttpSecurity.authorizeRequests的返回值
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
