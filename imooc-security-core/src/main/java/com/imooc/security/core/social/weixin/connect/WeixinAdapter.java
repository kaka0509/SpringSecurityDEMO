package com.imooc.security.core.social.weixin.connect;

import com.imooc.security.core.social.weixin.api.Weixin;
import com.imooc.security.core.social.weixin.api.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 将微信数据结构转换为标准数据结构
 * Created by Skye on 2019/6/23.
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {


    private String openId;

    public WeixinAdapter() {
    }

    public WeixinAdapter(String openId) {
        this.openId = openId;
    }


    @Override
    public boolean test(Weixin api) {
        return true;
    }

    /**
     * 通过api取得用户信息，然后设置到connection里面
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(Weixin api, ConnectionValues values) {
        WeixinUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Weixin api) {
        return null;
    }

    @Override
    public void updateStatus(Weixin api, String message) {
        //do nothing
    }
}
