package com.andmobi.recuc_keybox.Login;

import com.andmobi.recuc_keybox.Login.LoginContract.Presenter;
import com.andmobi.recuc_keybox.data.Constant;
import com.andmobi.recuc_keybox.data.net.Network;
import com.andmobi.recuc_keybox.modle.Base;
import com.andmobi.recuc_keybox.modle.BaseList;
import com.andmobi.recuc_keybox.modle.KeyBox;
import com.andmobi.recuc_keybox.modle.LoginInfo;
import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.Utils;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 16:07
 */
public class LoginPresenter implements Presenter {


    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view) {
        this.mView = view;
        mView = Utils.checkNotNull(view, "mView cannot be null!");
        mView.setPresenter(this);

    }


    @Override
    public Observable<KeyBox> getKeyBoxInfo() {

        return Network.getMainApi().getKeyBoxInfo("6fb4f13eb7760f1f")
                .map(new Func1<BaseList<KeyBox>, KeyBox>() {
                    @Override
                    public KeyBox call(BaseList<KeyBox> keyboxs) {
                        return keyboxs.getDatas().get(0);
                    }
                });


    }

    @Override
    public void showUserWxLogin() {

        final String url = Constant.BASEURL + Constant.APP_SERVICE_URL_WXQRCODE;
        getKeyBoxInfo()
                .subscribeOn(Schedulers.io())//生产线程
                .observeOn(AndroidSchedulers.mainThread())//消费线程
                .subscribe(new Action1<KeyBox>() {
                    @Override
                    public void call(KeyBox keybox) {
                        mView.onShowUserWxLogin(url + keybox.getId());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void showNotNet() {

        mView.onShowNotNet();
    }

    @Override
    public void wxPoll() {
        getKeyBoxInfo()
                .flatMap(new Func1<KeyBox, Observable<Base<LoginInfo>>>() {
                    @Override
                    public Observable<Base<LoginInfo>> call(KeyBox keyBox) {
                        return Network.getMainApi().pollwx(keyBox.getId());
                    }
                })
                .map(new Func1<Base<LoginInfo>, String>() {
                    @Override
                    public String call(Base<LoginInfo> loginInfoBase) {
                        return loginInfoBase.getStatus().toString();
                    }
                })
                .subscribeOn(Schedulers.io())//生产线程
                .observeOn(AndroidSchedulers.mainThread())//消费线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (s.equals("1")) {
                            DebugUtils.d(mView.getClass().getSimpleName(), "微信登录成功");
                            mView.onSuccessWxLogin();
                        } else
                            DebugUtils.d(mView.getClass().getSimpleName(), "没有微信登录");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

    @Override
    public void cycleWxPoll(RxFragment rxFragment) {

        Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())//生产线程
                .compose(rxFragment.<Long>bindUntilEvent(FragmentEvent.PAUSE))//订阅事件与Fragment生命周期同步
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        DebugUtils.d(mView.getClass().getSimpleName(), String.format("第%d次轮询", aLong));
                        wxPoll();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }


    @Override
    public void start() {

    }
}
