package com.andmobi.recuc_keybox.Login;

import com.andmobi.recuc_keybox.BasePresenter;
import com.andmobi.recuc_keybox.BaseView;
import com.andmobi.recuc_keybox.modle.KeyBox;
import com.trello.rxlifecycle.components.RxFragment;

import rx.Observable;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 15:53
 */
public class LoginContract {
    interface Presenter extends BasePresenter {

        Observable<KeyBox> getKeyBoxInfo();


        void showUserWxLogin();

        void showNotNet();

        void wxPoll();//请求微信二维码登录

        void cycleWxPoll(RxFragment rxFragment);//循环请求微信二维码登录

    }

    interface View extends BaseView<Presenter> {
        void onShowUserWxLogin(String url);//显示用户登录二维码

        void onShowNotNet();//无网络 显示文字

        void onSuccessWxLogin();//用户微信登录成功
    }
}
