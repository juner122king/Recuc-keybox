package com.andmobi.recuc_keybox.userOrder;

import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.Utils;
import com.trello.rxlifecycle.ActivityEvent;

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


    public OrderPresenter(OrderContract.View view) {

        mView = view;
        mView = Utils.checkNotNull(view, "mView cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void startCountdown() {

        Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        long l = 30;
                        return l - aLong;
                    }
                })
                .subscribeOn(Schedulers.io())//生产线程
                .compose(mView.getThis().<Long>bindUntilEvent(ActivityEvent.STOP))//订阅事件与Fragment生命周期同步
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        DebugUtils.d(mView.getClass().getSimpleName(), String.format("第%d次轮询", aLong));
                        mView.onSetNumber(aLong);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });


    }
}
