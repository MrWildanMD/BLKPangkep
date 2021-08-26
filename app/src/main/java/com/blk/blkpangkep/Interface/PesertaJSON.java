package com.blk.blkpangkep.Interface;

import com.blk.blkpangkep.Model.Peserta;
import com.blk.blkpangkep.Model.RssObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PesertaJSON {
    @GET("main/peserta.json")
    Call<Peserta> getPeserta();
}
