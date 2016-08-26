package com.andmobi.recuc_keybox;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

/**
 * Description:
 * Created by andmobi003 on 2016/8/3 15:52
 */
public class App extends Application {
    public static Context mContext;
    //设备唯一标识码
    public static String UUID;

    public final static long COUNTDOWN = 60 * 60;//用户操作时间

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        UUID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
    }


}
