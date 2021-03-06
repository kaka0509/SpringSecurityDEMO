package com.imooc.security.core.properties;

/**
 * 支持多个第三方在本应用进行认证，使用数组存储
 * @author Skye
 */
public class OAuth2Properties {

    private String jwtSigningKey = "imooc";

    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
