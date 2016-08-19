package com.andmobi.recuc_keybox.Login;

import com.andmobi.recuc_keybox.BasePresenter;
import com.andmobi.recuc_keybox.BaseView;
import com.andmobi.recuc_keybox.modle.KeyBox;

import rx.Observable;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 15:53
 */
public class LoginContract {
    interface Presenter extends BasePresenter {

        Observable<KeyBox> getKeyBoxInfo();


        void showUserWxLogin();

        void wxPoll();//请求微信二维码登录
        void cycleWxPoll();//循环请求微信二维码登录

    }

    interface View extends BaseView<Presenter> {
        void onShowUserWxLogin(String url);

        void onSuccessWxLogin();//用户微信登录成功
    }
}
