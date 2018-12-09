package com.frek2816.androidlab.Retrofit;

import com.frek2816.androidlab.Entity.ImageItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetImageDataService {
    @GET("list")
    Call<List<ImageItem>> getImageList();
}