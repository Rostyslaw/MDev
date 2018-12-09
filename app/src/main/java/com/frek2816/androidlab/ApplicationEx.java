package com.frek2816.androidlab;

import android.app.Application;

import com.frek2816.androidlab.Models.DetailsInteractorImpl;
import com.frek2816.androidlab.Models.ListInteractorImpl;
import com.frek2816.androidlab.Models.ModelsInteractor;
import com.frek2816.androidlab.Retrofit.GetImageDataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String BASE_URL = "https://picsum.photos/";

    private static ApplicationEx instance;
    private ManageFragments fragmentManager;
    private ManageStorage storageManager;
    private ModelsInteractor.ListInteractor listInteractor;
    private ModelsInteractor.DetailsInteractor detailsInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupItems();
    }

    public ModelsInteractor.DetailsInteractor getDetailsInteractor() {
        return detailsInteractor;
    }

    private void setupItems() {
        final GetImageDataService apiClient = createApiClient();
        storageManager = new ManageStorage(getApplicationContext());
        listInteractor = new ListInteractorImpl(apiClient);

        detailsInteractor = new DetailsInteractorImpl(storageManager);
    }

    public static ApplicationEx getInstance() {
        return instance;
    }

    private GetImageDataService createApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(GetImageDataService.class);
    }

    public void setFragmentManager(ManageFragments fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public ModelsInteractor.ListInteractor getListInteractor() {
        return listInteractor;
    }

    public ManageFragments getFragmentManager() {
        return fragmentManager;
    }
}
