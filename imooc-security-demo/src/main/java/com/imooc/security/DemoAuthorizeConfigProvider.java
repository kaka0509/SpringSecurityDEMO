package com.imooc.security;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 应用服务的URL安全配置
 * @author Skye
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider{
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        // 将自己写的RBAC模型和SpringSecurity融合
        // rbacService是在容器内的名字
       config.anyRequest().access("@rbacService.hasPermission(request, authentication)");
       // anyRequest()必须在最后生效
    }
}
