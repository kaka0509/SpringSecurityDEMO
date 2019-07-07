package com.imooc.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author Skye
 */
public class WeixinAccessGrant extends AccessGrant {

    private static final long serialVersionUID = -7243374526633186782L;

    /**
     * 存储微信走完OAuth2流程多返回的openId
     */
    private String openId;

    public WeixinAccessGrant( ) {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
