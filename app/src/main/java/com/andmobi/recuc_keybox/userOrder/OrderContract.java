package com.andmobi.recuc_keybox.userOrder;

import com.andmobi.recuc_keybox.BasePresenter;
import com.andmobi.recuc_keybox.BaseView;
import com.trello.rxlifecycle.components.RxActivity;

/**
 * Description:
 * Created by andmobi003 on 2016/8/22 17:42
 */
public class OrderContract {

    interface Presenter extends BasePresenter {


        void startCountdown();

    }

    interface View extends BaseView<Presenter> {
        void onHideSetList();

        void onHideGetList();

        void onShowSetList();

        void onShowGetList();

        void onSetNumber(Long number);

        RxActivity getThis();

        void onFinish();
    }
}
