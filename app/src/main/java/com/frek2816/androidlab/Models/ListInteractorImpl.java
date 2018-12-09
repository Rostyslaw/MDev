package com.frek2816.androidlab.Models;

import com.frek2816.androidlab.Retrofit.GetImageDataService;
import com.frek2816.androidlab.Entity.ImageItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInteractorImpl implements ModelsInteractor.ListInteractor {
    private GetImageDataService service;

    public ListInteractorImpl(GetImageDataService service){
        this.service = service;
    }
    public void getData(final ModelsInteractor.ListInteractor.OnFinishedListener onFinishedListener){
        Call<List<ImageItem>> call = service.getImageList();
        call.clone().enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>> call, Response<List<ImageItem>> response) {
                    onFinishedListener.onFinishedLoad(response.body());
            }
            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
