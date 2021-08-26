package com.blk.blkpangkep.Network;

import com.blk.blkpangkep.Interface.Api;
import com.blk.blkpangkep.Interface.PesertaJSON;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientTwo {
    private static RetrofitClientTwo instance = null;
    private PesertaJSON myApi;
    public native String BASEURLTWO();

    private RetrofitClientTwo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURLTWO())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(PesertaJSON.class);
    }

    public static synchronized RetrofitClientTwo getInstance() {
        if (instance == null) {
            instance = new RetrofitClientTwo();
        }
        return instance;
    }

    public PesertaJSON getMyApi() {
        return myApi;
    }
}
