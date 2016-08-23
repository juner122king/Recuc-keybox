package com.andmobi.recuc_keybox.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.userOrder.OrderActivity;
import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.ImageLoader;
import com.andmobi.recuc_keybox.util.Utils;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 14:31
 */
public class LoginMainFragment extends RxFragment implements LoginContract.View {
    LoginContract.Presenter mPresenter;
    ImageView mIv_code;//二维码控件
    TextView mTextView_notNet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugUtils.d(getClass().getSimpleName(), "onCreateView");
        View root = inflater.inflate(R.layout.fragment_login_main, container, false);
        mIv_code = (ImageView) root.findViewById(R.id.iv_wx);
        mTextView_notNet = (TextView) root.findViewById(R.id.tv_notnetwork);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DebugUtils.d(getClass().getSimpleName(), "onResume");
        mPresenter.cycleWxPoll(this);
    }

    /**
     * @param url 二维码url
     * @deprecated 显示用户登录二维码
     */
    @Override
    public void onShowUserWxLogin(String url) {

        mTextView_notNet.setVisibility(View.GONE);
        mIv_code.setVisibility(View.VISIBLE);

        ImageLoader.getInstance().dispplayImage(url, mIv_code);

    }

    @Override
    public void onShowNotNet() {
        mIv_code.setVisibility(View.GONE);
        mTextView_notNet.setVisibility(View.VISIBLE);

    }

    /**
     * 用户扫码成功 跳转activity
     */
    @Override
    public void onSuccessWxLogin(String token) {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = Utils.checkNotNull(presenter);
    }

}
