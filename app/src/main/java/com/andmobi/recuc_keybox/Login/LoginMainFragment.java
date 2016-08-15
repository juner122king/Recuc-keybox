package com.andmobi.recuc_keybox.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.Utils;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 14:31
 */
public class LoginMainFragment extends Fragment implements LoginContract.View {
    LoginContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugUtils.d(getClass().getSimpleName(), "onCreateView");
        View root = inflater.inflate(R.layout.fragment_login_main, container, false);
        return root;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = Utils.checkNotNull(presenter);
    }



    /**
     * @param url 二维码url
     * @deprecated 显示用户登录二维码
     */
    @Override
    public void onShowUserWxLogin(String url) {

    }
}
