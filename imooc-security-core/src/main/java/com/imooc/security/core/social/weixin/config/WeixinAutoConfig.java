package com.imooc.security.core.social.weixin.config;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.WeixinProperties;
import com.imooc.security.core.social.ImoocConnectView;
import com.imooc.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @author Skye
 * 注解解析 ： @ConditionalOnProperty(prefix = "imooc.security.social.qq",name = "app-id")
 * imooc.security.social.qq.app-id 的值存在的情况下，下面的配置才会生效
 */
@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
        return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

    /**
     * 当spring容器中存在名字为weixinConnectedView的bean则下面配置不生效
     * connect/weixinConnect 解绑视图
     * connect/weixinConnected 绑定视图
     * @return
     */
    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new ImoocConnectView();
    }
}
