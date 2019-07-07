package com.imooc.security.core.social.weixin.api;

/**
 * 用于获取用户信息的接口
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
