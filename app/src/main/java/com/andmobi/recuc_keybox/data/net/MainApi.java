package com.andmobi.recuc_keybox.data.net;

import com.andmobi.recuc_keybox.modle.Base;
import com.andmobi.recuc_keybox.modle.BaseList;
import com.andmobi.recuc_keybox.modle.KeyBox;
import com.andmobi.recuc_keybox.modle.LoginInfo;
import com.andmobi.recuc_keybox.modle.UserOrder;
import com.andmobi.recuc_keybox.modle.UserOrder2;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    /**
     * 获取用户钥匙列表
     *
     * @param keyboxId 钥匙箱id  获取：getKeyBoxInfo().getId()
     * @param token    用户登录后的唯一   获取：pollwx().getToken()
     * @param status   用于识别取或放:
     *                 status=5  取  get
     *                 status=1  放  set
     */
    @GET("core/order/query")
    Observable<BaseList<List<UserOrder>>> queryUserKeyList(@Query("token") String token, @Query("keyboxId") String keyboxId, @Query("status") int status);


    @FormUrlEncoded
    @POST("core/order/takeKey")
    Observable<Base<UserOrder2>> keyOperate(@Field("orderNo") String orderNo, @Field("optType") int optType, @Field("token") String token, @Field("keyboxId") int keyboxId);
}
