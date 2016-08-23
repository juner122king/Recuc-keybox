package com.andmobi.recuc_keybox.userOrder;

import com.andmobi.recuc_keybox.App;
import com.andmobi.recuc_keybox.data.net.Network;
import com.andmobi.recuc_keybox.modle.BaseList;
import com.andmobi.recuc_keybox.modle.KeyBox;
import com.andmobi.recuc_keybox.modle.UserOrder;
import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.Utils;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by andmobi003 on 2016/8/22 17:42
 */
public class OrderPresenter implements OrderContract.Presenter {


    private OrderContract.View mView;

    private long countdown;
    private int optType;
    private static final int STATUS_GAT = 5;
    private static final int STATUS_SAT = 1;

    public OrderPresenter(OrderContract.View view) {

        mView = view;
        mView = Utils.checkNotNull(view, "mView cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.onHideGetListView();
        mView.onHideSetListView();
        mView.onInitView();
    }

    @Override
    public void startCountdown() {
        initCountdown();
        Observable
                .interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return countdown--;
                    }
                })
                .subscribeOn(Schedulers.io())//生产线程
                .compose(mView.getThis().<Long>bindUntilEvent(ActivityEvent.STOP))//订阅事件与Fragment生命周期同步
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        DebugUtils.d(mView.getClass().getSimpleName(), String.format("第%d次轮询", aLong));
                        if (aLong > 0)
                            mView.onSetNumber(aLong);
                        else
                            mView.onFinish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

    @Override
    public void initCountdown() {

        countdown = App.COUNTDOWN;

    }

    @Override
    public void getOrderList() {
        optType = 1;
        Network.getMainApi().getKeyBoxInfo(App.UUID)
                .map(new Func1<BaseList<KeyBox>, KeyBox>() {
                    @Override
                    public KeyBox call(BaseList<KeyBox> keyboxs) {
                        return keyboxs.getDatas().get(0);
                    }
                })
                .flatMap(new Func1<KeyBox, Observable<BaseList<List<UserOrder>>>>() {
                    @Override
                    public Observable<BaseList<List<UserOrder>>> call(KeyBox keyBox) {
                        return Network.getMainApi().queryUserKeyList(mView.getToken(), String.valueOf(keyBox.getId()), STATUS_GAT);
                    }
                })
                .filter(new Func1<BaseList<List<UserOrder>>, Boolean>() {
                    @Override
                    public Boolean call(BaseList<List<UserOrder>> userOrderBaseList) {
                        return userOrderBaseList.getStatus() == 1;
                    }
                })
                .map(new Func1<BaseList<List<UserOrder>>, List<UserOrder>>() {
                    @Override
                    public List<UserOrder> call(BaseList<List<UserOrder>> listBaseList) {
                        return toListUserOrder(listBaseList);
                    }
                })
                .subscribeOn(Schedulers.io())//生产线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<UserOrder>>() {
                    @Override
                    public void call(List<UserOrder> userOrders) {

                        if (userOrders.size() == 0)
                            mView.onHideGetListView();
                        else {

                            mView.onShowGetListView();
                            mView.onShowGetOrderListData(userOrders, optType);
                        }
                    }
                });

    }

    @Override
    public void setOrderList() {
        optType = 2;
        Network.getMainApi().queryUserKeyList(mView.getToken(), "-1", STATUS_SAT)
                .filter(new Func1<BaseList<List<UserOrder>>, Boolean>() {
                    @Override
                    public Boolean call(BaseList<List<UserOrder>> userOrderBaseList) {
                        return userOrderBaseList.getStatus() == 1;
                    }
                })
                .map(new Func1<BaseList<List<UserOrder>>, List<UserOrder>>() {
                    @Override
                    public List<UserOrder> call(BaseList<List<UserOrder>> listBaseList) {

                        return toListUserOrder(listBaseList);

                    }
                })
                .subscribeOn(Schedulers.io())//生产线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<UserOrder>>() {
                    @Override
                    public void call(List<UserOrder> userOrders) {

                        if (userOrders.size() == 0)
                            mView.onHideSetListView();
                        else {
                            mView.onShowSetListView();
                            mView.onShowSetOrderListData(userOrders, optType);
                        }
                    }
                });
    }

    private List<UserOrder> toListUserOrder(BaseList<List<UserOrder>> listBaseList) {
        List<UserOrder> userOrders = new ArrayList<>();
        for (List<UserOrder> orders : listBaseList.getDatas()) {
            userOrders.addAll(orders);
        }
        return userOrders;
    }

}
