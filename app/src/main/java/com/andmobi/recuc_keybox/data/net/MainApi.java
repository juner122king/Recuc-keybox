package com.andmobi.recuc_keybox.data.net;

import com.andmobi.recuc_keybox.modle.Base;
import com.andmobi.recuc_keybox.modle.BaseList;
import com.andmobi.recuc_keybox.modle.KeyBox;
import com.andmobi.recuc_keybox.modle.LoginInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description:
 * Created by andmobi003 on 2016/7/20 15:01
 */
public interface MainApi {

    /**
     * 获取钥匙箱信息
     *
     * @param uuid 代表钥匙箱的唯一物理id
     */

    @GET("keybox/location")
    Observable<BaseList<KeyBox>> getKeyBoxInfo(@Query("id") String uuid);


    /**
     * 钥匙箱微信扫码登录轮询
     *
     * @param keyboxId
     */
    @GET("core/login/wxPoll")
    Observable<Base<LoginInfo>> pollwx(@Query("keyboxId") int keyboxId);


}
