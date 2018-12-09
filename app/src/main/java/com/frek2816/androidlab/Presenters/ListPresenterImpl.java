package com.frek2816.androidlab.Presenters;

import com.frek2816.androidlab.ApplicationEx;
import com.frek2816.androidlab.Entity.ImageItem;
import com.frek2816.androidlab.Models.ModelsInteractor;
import com.frek2816.androidlab.Views.ViewInterfaces;

import java.util.List;

public class ListPresenterImpl implements PresenterInterfaces.ListPresenter,
        ModelsInteractor.ListInteractor.OnFinishedListener {
     private ViewInterfaces.ListImagesView view;
     private ModelsInteractor.ListInteractor interactor;

     public ListPresenterImpl(ViewInterfaces.ListImagesView view){
         this.view = view;
         interactor = ApplicationEx.getInstance().getListInteractor();
     }


     @Override
    public void getData(){
         interactor.getData(this);
     }

    @Override
    public void onFinishedLoad(List<ImageItem> imageItemList) {
        if (view != null) {
            view.setData(imageItemList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.onResponseFailure(t);
        }
    }






}
