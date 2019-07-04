package com.imooc.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL_GET_USER_INFO = "";


    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        return null;
    }
}
