package com.andmobi.recuc_keybox.Login;

import com.andmobi.recuc_keybox.BasePresenter;
import com.andmobi.recuc_keybox.BaseView;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 15:53
 */
public class LoginContract {
    interface Presenter extends BasePresenter {

        void showUserWxLogin();


    }

    interface View extends BaseView<Presenter> {
        void onShowUserWxLogin(String url);
    }
}
