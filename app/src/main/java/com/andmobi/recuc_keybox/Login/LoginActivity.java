package com.andmobi.recuc_keybox.Login;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.receiver.ConnectionChangeReceiver;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 14:30
 */
public class LoginActivity extends Activity {
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    LoginMainFragment loginMainFragment;

    ConnectionChangeReceiver connectionChangeReceiver;


    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    void initView() {
        loginMainFragment = new LoginMainFragment();
        getFragmentManager().beginTransaction().replace(R.id.rl, loginMainFragment).commit();
        mLoginPresenter = new LoginPresenter(loginMainFragment);

        //动态注册广播消息
        connectionChangeReceiver = new ConnectionChangeReceiver(handler);
        registerReceiver(connectionChangeReceiver, new IntentFilter(ACTION));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectionChangeReceiver);
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(LoginActivity.this, "1", Toast.LENGTH_SHORT).show();
                    mLoginPresenter.showUserWxLogin();
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this, "2", Toast.LENGTH_SHORT).show();
                    mLoginPresenter.showNotNet();
                    break;
            }

        }
    };
}
