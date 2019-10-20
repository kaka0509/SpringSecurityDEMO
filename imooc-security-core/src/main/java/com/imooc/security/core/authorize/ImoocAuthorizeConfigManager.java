package com.imooc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author Skye
 */
@Component
public class ImoocAuthorizeConfigManager implements AuthorizeConfigManager {

    // 收集spring容器中所有的AuthorizeConfigProvider
    @Autowired
    List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
        // 剩下的所有请求都需要经过认证
        config.anyRequest().authenticated();
    }
}
