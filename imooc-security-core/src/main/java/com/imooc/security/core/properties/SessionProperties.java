package com.imooc.security.core.properties;

/**
 * session配置
 */
public class SessionProperties {

    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maxmumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionPreventsLogin;

    /**
     * Session失效跳转地址
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public int getMaxmumSessions() {
        return maxmumSessions;
    }

    public void setMaxmumSessions(int maxmumSessions) {
        this.maxmumSessions = maxmumSessions;
    }

    public boolean isMaxSessionPreventsLogin() {
        return maxSessionPreventsLogin;
    }

    public void setMaxSessionPreventsLogin(boolean maxSessionPreventsLogin) {
        this.maxSessionPreventsLogin = maxSessionPreventsLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }
}
