package com.imooc.security.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 使用 @EnableAuthorizationServer 指定应用为OAuth2认证服务器
 * @author Skye
 */
@Configuration
@EnableAuthorizationServer
public class ImoocAuthorizationServerConfig {
}
