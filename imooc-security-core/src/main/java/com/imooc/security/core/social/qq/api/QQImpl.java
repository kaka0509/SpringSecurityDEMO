package com.imooc.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId; //通过accessToken获取

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 通过输入在上一步获取的Access Token，得到对应用户身份的OpenID。
     * OpenID是此网站上或应用中唯一对应用户身份的标识。
     * 网站或应用可将此ID进行存储，便于用户下次登录时辨识其身份，或将其与用户在网站上或应用中的原有账号进行绑定。
     */
    public QQImpl(String accessToken, String appId) {
        //token存储在请求参数里
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);

        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("获取openId的结果是: {}", result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("获取用户信息的结果是: {}", result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
