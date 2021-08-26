package com.blk.blkpangkep.Interface;

import com.blk.blkpangkep.Model.RssObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("v1/api.json?rss_url=https%3A%2F%2Fid.indeed.com%2Frss%3Fq%3Dlowongan%2Bkerja%26l%3DMakassar")
    Call<RssObject> getRss();
}
