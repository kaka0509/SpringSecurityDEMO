package com.imooc.security.browser;

import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.authorize.AuthorizeConfigManager;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig { //继承基础配置

    //自定义安全配置
    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    // import other config
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    // 注入自定义的社交登录安全配置
    @Autowired
    private SpringSocialConfigurer immocSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler imoocLogoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(immocSocialSecurityConfig)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository()) //token存储策略
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())// 过期时间
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy) //session失效策略
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaxmumSessions()) //最大登录数
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionPreventsLogin()) //当最大登录数达到时阻止登录
                    .expiredSessionStrategy(sessionInformationExpiredStrategy) //并发登录失效策略
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessHandler(imoocLogoutSuccessHandler)
                    .deleteCookies("JSESSIONID") //要删除的cookie的名字
                    .and()
//                .authorizeRequests() //这句下面的部分都是授权的配置
//                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                        securityProperties.getBrowser().getLoginPage(),
//                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//                        securityProperties.getBrowser().getSignUpUrl(),
//                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
//                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
//                        "/user/regist")
//                        .permitAll() //这些请求不控制
//                    .antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN") // 表示只有ADMIN权限可以访问/user这个Get请求,权限列表在UserDetailService里面
//                    .anyRequest()  //其他的所有请求
//                    .authenticated() //都需要身份验证
//                    .and()
//                 使用AuthorizeConfigManager 上面代码已经用不上
                .csrf().disable();//跨站请求防护关闭

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        //JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        InMemoryTokenRepositoryImpl tokenRepository = new InMemoryTokenRepositoryImpl();
        return tokenRepository;
    }
}
