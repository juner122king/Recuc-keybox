package com.andmobi.recuc_keybox.data.net;

import com.andmobi.recuc_keybox.App;
import com.andmobi.recuc_keybox.data.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Created by andmobi003 on 2016/7/20 15:15
 */
public class Network {
    private static MainApi mainApi;

    public static MainApi getMainApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (mainApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(MyOkHttpClient.getInstance(App.mContext).getOkHttpClient())
                    .baseUrl(Constant.REST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mainApi = retrofit.create(MainApi.class);
        }

        return mainApi;
    }
}
