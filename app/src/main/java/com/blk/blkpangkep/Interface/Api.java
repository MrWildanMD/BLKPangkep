package com.blk.blkpangkep.Interface;

import com.blk.blkpangkep.Model.RssObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("v1/api.json?rss_url=http%3A%2F%2Frss.careerjet.co.id%2Frss%3Fs%3DMakassar%26l%3DIndonesia%26lid%3D113794%26psz%3D50%26snl%3D100%26wos%3D1%26wct%3D1%26wcp%3D1")
    Call<RssObject> getRss();
}
