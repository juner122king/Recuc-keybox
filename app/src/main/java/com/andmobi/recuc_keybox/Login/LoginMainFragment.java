package com.andmobi.recuc_keybox.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.util.DebugUtils;
import com.andmobi.recuc_keybox.util.ImageLoader;
import com.andmobi.recuc_keybox.util.Utils;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 14:31
 */
public class LoginMainFragment extends Fragment implements LoginContract.View {
    LoginContract.Presenter mPresenter;
    ImageView mIv_code;//二维码控件

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugUtils.d(getClass().getSimpleName(), "onCreateView");
        View root = inflater.inflate(R.layout.fragment_login_main, container, false);
        mIv_code = (ImageView) root.findViewById(R.id.iv_wx);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.showUserWxLogin();
    }

    /**
     * @param url 二维码url
     * @deprecated 显示用户登录二维码
     */
    @Override
    public void onShowUserWxLogin(String url) {
        ImageLoader.getInstance().dispplayImage(url, mIv_code);

        mPresenter.cycleWxPoll();
    }

    @Override
    public void onSuccessWxLogin() {

    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = Utils.checkNotNull(presenter);
    }

}
