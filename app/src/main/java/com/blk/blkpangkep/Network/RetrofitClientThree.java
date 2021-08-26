package com.blk.blkpangkep.Network;

import com.blk.blkpangkep.Interface.PesertaJSON;
import com.blk.blkpangkep.Interface.YoutubeAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientThree {
    private static RetrofitClientThree instance = null;
    private YoutubeAPI myApi;
    public native String BASEURLTHREE();

    private RetrofitClientThree() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURLTHREE())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(YoutubeAPI.class);
    }

    public static synchronized RetrofitClientThree getInstance() {
        if (instance == null) {
            instance = new RetrofitClientThree();
        }
        return instance;
    }

    public YoutubeAPI getMyApi() {
        return myApi;
    }
}
