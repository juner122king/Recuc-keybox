package com.andmobi.recuc_keybox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.andmobi.recuc_keybox.util.DebugUtils;

/**
 * Description:
 * Created by andmobi003 on 2016/8/15 16:13
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    NetworkInfo.State wifiState = null;
    NetworkInfo.State mobileState = null;
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    Handler handler;

    public ConnectionChangeReceiver(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.e(context.getClass().getSimpleName(), "网络状态改变");
        if (ACTION.equals(intent.getAction())) {
            //获取手机的连接服务管理器，这里是连接管理器类
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
                DebugUtils.d("network", "移动网络连接成功");
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);

            } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {

                DebugUtils.d("network", "无线网络连接成功");
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {

                DebugUtils.d("network", "没有任何网络...");
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);

            }
        }
    }
}
