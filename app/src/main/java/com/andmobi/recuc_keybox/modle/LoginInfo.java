package com.andmobi.recuc_keybox.modle;

/**
 * Description:
 * Created by andmobi003 on 2016/8/19 17:51
 */
public class LoginInfo {

    /**
     * 会员凭证
     */
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "token='" + token + '\'' +
                '}';
    }
}
