package com.andmobi.recuc_keybox.userOrder;

import com.andmobi.recuc_keybox.BasePresenter;
import com.andmobi.recuc_keybox.BaseView;
import com.andmobi.recuc_keybox.modle.UserOrder;
import com.trello.rxlifecycle.components.RxActivity;

import java.util.List;

/**
 * Description:
 * Created by andmobi003 on 2016/8/22 17:42
 */
public class OrderContract {

    interface Presenter extends BasePresenter {


        void startCountdown();

        void initCountdown();

        void getOrderList();

        void setOrderList();

        void keyAiton(int ownerViliageId, String getOwnerViliage, String orderNo, int optType);
    }

    interface View extends BaseView<Presenter> {
        void onHideSetListView();

        void onHideGetListView();

        void onShowSetListView();

        void onShowGetListView();

        void onSetNumber(Long number);


        void onFinish();

        void onInitView();

        void onShowGetOrderListData(List<UserOrder> orderList, int optType);

        void onShowSetOrderListData(List<UserOrder> orderList, int optType);


        RxActivity getThis();

        String getToken();
    }
}
