package com.blk.blkpangkep.Interface;

import com.blk.blkpangkep.Model.Youtube.Galeri;
import com.blk.blkpangkep.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YoutubeAPI {
    @GET("search?part=snippet&channelId=UC083RbNZ3TxuIZqdlMrvd7w&key=AIzaSyCTm486D1aOoyy83FDJZ3DrXqX8fGTwnjc")
    Call<Galeri> getGaleri();
}
